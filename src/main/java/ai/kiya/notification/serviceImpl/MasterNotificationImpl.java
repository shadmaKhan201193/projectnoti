package ai.kiya.notification.serviceImpl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import ai.kiya.notification.config.AppConstant;
import ai.kiya.notification.dto.NotificationExtVO;
import ai.kiya.notification.dto.NotificationVO;
import ai.kiya.notification.dto.UserMst;
import ai.kiya.notification.entity.MasterNotification;
import ai.kiya.notification.entity.NotificationMst;
import ai.kiya.notification.repo.LoanRepository;
import ai.kiya.notification.repo.MasterNotificationRepo;
import ai.kiya.notification.service.EmailService;
import ai.kiya.notification.service.MasterNotificationService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MasterNotificationImpl implements MasterNotificationService {

	private Logger log= LoggerFactory.getLogger(MasterNotificationImpl.class);
	
	@Autowired
	MasterNotificationRepo masterNotificationRepo;

	@Autowired
	private EmailService emailService;

	@Value("${user.details.api}")
	private String userDetailsApiUrl;
	
	@Autowired
	LoanRepository loanRepository;

	@Override
	public NotificationMst getByNotificationId(Integer notificationId) {

		NotificationMst masterNotification = masterNotificationRepo.findByNotificationId(notificationId);
		if (null != masterNotification) {
			masterNotification.setReadYN(true);
			masterNotificationRepo.save(masterNotification);
			return masterNotification;
		} else
			return null;

	}

	@Override
	public List<NotificationMst> getByReadYN(Boolean readYN) {

		List<NotificationMst> masterNotification = masterNotificationRepo.findByReadYN(readYN);
		if (null != masterNotification) {
			return masterNotification;
		} else
			return null;
	}

	@Override
	public void sendNotification(NotificationVO notificationVO, String type) {
		UserMst user = null;
		try {
			switch (type) {
			case AppConstant.NOTIFICATION_TYPE_EMAIL:
				if (notificationVO instanceof NotificationExtVO) {
					emailService.sendEmail(((NotificationExtVO) notificationVO).getTo(),
							((NotificationExtVO) notificationVO).getCc(), ((NotificationExtVO) notificationVO).getBcc(),
							notificationVO.getSubject(), notificationVO.getMessage());
				}
				else {
					user = getUser(notificationVO);
					emailService.sendEmail(user.getEmailId(), notificationVO.getSubject(), notificationVO.getMessage());
				}
				break;
			case AppConstant.NOTIFICATION_TYPE_SMS:
				// We dont have sms gateway yet, so sending email instead
				if (notificationVO instanceof NotificationExtVO) {
					emailService.sendEmail(null, null, null, "sms:" + notificationVO.getSubject(),
							notificationVO.getMessage());
				} else {
					user = getUser(notificationVO);
					emailService.sendEmail(user.getEmailId(), "sms:" + notificationVO.getSubject(),
							notificationVO.getMessage());
				}
				break;
			case AppConstant.NOTIFICATION_TYPE_EMAIL_SMS:
				if (notificationVO instanceof NotificationExtVO) {
					emailService.sendEmail(((NotificationExtVO) notificationVO).getTo(),((NotificationExtVO) notificationVO).getCc(), ((NotificationExtVO) notificationVO).getBcc(),notificationVO.getSubject(), notificationVO.getMessage());
					emailService.sendEmail(null, null, null, "sms:" + notificationVO.getSubject(),notificationVO.getMessage());
				} else {
					user = getUser(notificationVO);
					emailService.sendEmail(user.getEmailId(), notificationVO.getSubject(), notificationVO.getMessage());
					// We dont have sms gateway yet, so sending email instead
					emailService.sendEmail(user.getEmailId(), "sms:" + notificationVO.getSubject(),
							notificationVO.getMessage());
				}
				break;
			case AppConstant.NOTIFICATION_TYPE_APP:
				user = getUser(notificationVO);
				NotificationMst notificationMst = new NotificationMst();
				notificationMst.setMailId(user.getEmailId());
				notificationMst.setSourceUserId(notificationVO.getUserId());
				notificationMst.setMessage(notificationVO.getMessage());
				notificationMst.setNotificationCreationTd(new Date());
				notificationMst.setAppName(notificationVO.getSourceApp());
				notificationMst.setReadYN(false);
				masterNotificationRepo.save(notificationMst);
				break;
			default:
				break;
			}
		} catch (RestClientException | URISyntaxException e) {
			log.error("Unable to fetch user details for : ", notificationVO.getUserId());
		}
	}

	private UserMst getUser(NotificationVO notificationVO) throws URISyntaxException {
		UserMst user;
		ResponseEntity<UserMst> userEntity = new RestTemplate()
				.getForEntity(new URI(userDetailsApiUrl + notificationVO.getUserId()), UserMst.class);
		user = userEntity.getBody();
		if (user == null) {
			log.error("User details not found for : ", notificationVO.getUserId());
			return null;
		}
		return user;
	}

	
	
	
	@Override
	public List<NotificationMst> getAll() {
	
		List<NotificationMst>    masterNotification=masterNotificationRepo.findAll();
		if(null!=masterNotification) {
			return masterNotification;
		}
		else
		return null;
	}

	@Override
	public List<Object[]> getAllLoanLimitAndEmail(String prdAcctId) {
		List<Object[]> obj=loanRepository.getLoanLimitAndEmail(prdAcctId);
		return null;
	}

}
