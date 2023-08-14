package ai.kiya.notification.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import ai.kiya.notification.config.AppConstant;
import ai.kiya.notification.dto.NotificationExtVO;
import ai.kiya.notification.entity.EmailDetailsMst;
import ai.kiya.notification.entity.MasterNotification;
import ai.kiya.notification.entity.NotificationMst;
import ai.kiya.notification.entity.Response;
import ai.kiya.notification.repo.MasterNotificationRepo;
import ai.kiya.notification.service.MasterNotificationService;
import ai.kiya.notification.serviceImpl.NotificationHelperServiceImpl;



@RestController
//@Slf4j
public class NotificationController {
	
	private Logger log = LoggerFactory.getLogger(NotificationController.class);

	@Autowired
	MasterNotificationService masterNotificationService;
	
	@Autowired
	NotificationHelperServiceImpl notificationHelperService;
	
	
	@Autowired
	MasterNotificationRepo masterNotificationRepo;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@GetMapping(value = "/notification/{notificationId}")
	public NotificationMst getNotification(@PathVariable Integer notificationId) {
		NotificationMst MasterNotification = masterNotificationService.getByNotificationId(notificationId);
		return MasterNotification;

	}

	@GetMapping(value = "/readYesNo/{readYN}")
	public List<NotificationMst> getReadYesNo(@PathVariable Boolean readYN) {
		List<NotificationMst> MasterNotification = masterNotificationService.getByReadYN(readYN);
		return MasterNotification;
	}

	@PostMapping(value = "/notification/send")
	public String sendNotification(@RequestBody NotificationExtVO notificationVO) {
		if (notificationVO != null) {
			if (AppConstant.NOTIFICATION_TYPE_EMAIL.equals(notificationVO.getType())
					|| AppConstant.NOTIFICATION_TYPE_SMS.equals(notificationVO.getType())
					|| AppConstant.NOTIFICATION_TYPE_EMAIL_SMS.equals(notificationVO.getType())) {
		masterNotificationService.sendNotification(notificationVO, notificationVO.getType());
			} else {
				log.error("Incorrect notification type not mentioned in the request");
				return "Incorrect notification type not mentioned in the request";
			} 
		}
		return "Notification request registered successfully.";
	}
	
	
	
	@PostMapping(value = "/notification/sendEmail")   
	public ResponseEntity<String> sendEmail(@RequestBody Map<String, Object> inputMap) throws IOException {
	    EmailDetailsMst mst = notificationHelperService.sendNotification(inputMap);
	    if (mst != null) {
	        return ResponseEntity.ok("Email sent successfully"); // 200 OK status
	    } else {	    	
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email"); 
	    }
	}
	//--------------------------added by shadma 
	
	
	@PostMapping(value = "/saveDetails")
	public String saveDetails(@RequestBody  String jsonresonse) throws IOException, MessagingException {
		Response response = null;
		response = objectMapper.readValue(jsonresonse.getBytes(), Response.class);

		NotificationMst masterNotification=new NotificationMst();
		masterNotification.setMailId(response.getSource());
		masterNotification.setCcEmailId("Test@kiya.ai");
		masterNotification.setSourceUserId(response.getId());
		masterNotification.setNotificationCreationTd(new Date());
		masterNotification.setStringJson(jsonresonse);
		masterNotification.setMessage(response.getMessage());
		masterNotification.setRole("ADMIN");
		masterNotification.setAppName("Creation");
		masterNotification.setReadYN(false);
		NotificationMst res  =masterNotificationRepo.save(masterNotification);
		if(res!=null) {
			
			
			return objectMapper.writeValueAsString(res);
		}
		else {
			return "fail";
		}
	}
	
	//-------------------get all data from table
	@GetMapping(value = "/getAllRecord")
	public List<NotificationMst> getAllRecord() {
		List<NotificationMst> MasterNotification = masterNotificationService.getAll();
		return MasterNotification;
	}
	
	
	@PostMapping(value = "/notification/sendTracerEmail")   
	public ResponseEntity<String> sendTracerEmail(@RequestBody Map<String, Object> inputMap) throws IOException {
		
		
		
		return null;
	   
	}

}
