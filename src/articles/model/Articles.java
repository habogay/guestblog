package articles.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

@PersistenceCapable
public class Articles {
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@PrimaryKey
	private Key key;
	@Persistent
	private String articleId;
	@Persistent
	private String title;
	@Persistent
	private String alias;
	@Persistent
	private Text content;
	@Persistent
	private Text keyword;
	@Persistent
	private String author;
	@Persistent
	private String authorAlias;
	@Persistent
	private String email;
	@Persistent
	private Date date;
	@Persistent
	private String link;
	@Persistent
	private int view;
	@Persistent
	private int vodeYes;
	@Persistent
	private int vodeNo;
	@Persistent
	private String categoryAlias;
	@Persistent
	private String linkEmbed;

	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getAuthorAlias() {
		return authorAlias;
	}
	public void setAuthorAlias(String authorAlias) {
		this.authorAlias = authorAlias;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public int getVodeYes() {
		return vodeYes;
	}
	public void setVodeYes(int vodeYes) {
		this.vodeYes = vodeYes;
	}
	public int getVodeNo() {
		return vodeNo;
	}
	public void setVodeNo(int vodeNo) {
		this.vodeNo = vodeNo;
	}
	public Text getKeyword() {
		return keyword;
	}
	public void setKeyword(Text keyword) {
		this.keyword = keyword;
	}
	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Text getContent() {
		return content;
	}
	public void setContent(Text content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getCategoryAlias() {
		return categoryAlias;
	}
	public void setCategoryAlias(String categoryAlias) {
		this.categoryAlias = categoryAlias;
	}
	public String getLinkEmbed() {
		return linkEmbed;
	}
	public void setLinkEmbed(String linkEmbed) {
		this.linkEmbed = linkEmbed;
	}
}
