package ai.kiya.notification.entity;

import java.io.Serializable;

public class Response implements Serializable {

    //private static final long serialVersionUID = 1L;
	
	 private String source;
	    private String message;
	    private String Id;
		public String getSource() {
			return source;
		}
		public void setSource(String source) {
			this.source = source;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getId() {
			return Id;
		}
		public void setId(String id) {
			Id = id;
		}
		public Response(String source, String message, String id) {
			super();
			this.source = source;
			this.message = message;
			Id = id;
		}
		public Response() {
			super();
			// TODO Auto-generated constructor stub
		}
		
	

}
