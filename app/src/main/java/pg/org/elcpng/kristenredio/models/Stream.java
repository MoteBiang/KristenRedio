package pg.org.elcpng.kristenredio.models;

public class Stream {

	private long NoticeID;
	private int NoticeType;
	private int NoticeFrom;
	private String Title;
	private String Message;
	private String ImageURL;
	private String VideoURL;
	private int Status;
	private long timeStamp;
	
	public long getNoticeID() {
		return NoticeID;
	}
	public void setNoticeID(long id) {
		NoticeID = id;
	}
	public int getNoticeType() {
		return NoticeType;
	}
	public void setNoticeType(int noticeType) {
		NoticeType = noticeType;
	}
	public int getNoticeFrom() {
		return NoticeFrom;
	}
	public void setNoticeFrom(int noticeFrom) {
		NoticeFrom = noticeFrom;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public String getImageURL() {
		return ImageURL;
	}
	public void setImageURL(String imageURL) {
		ImageURL = imageURL;
	}
	public String getVideoURL() {
		return VideoURL;
	}
	public void setVideoURL(String videoURL) {
		VideoURL = videoURL;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
}
