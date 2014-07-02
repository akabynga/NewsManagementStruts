package by.epam.news.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "NEWS")
public class News implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
	@SequenceGenerator(name = "SEQ", sequenceName = "AUTOINC_SEQ")
	@Column(name = "ID")
	private int id;
	@Column(name = "TITLE", length = 100)
	private String title;
	@Column(name = "BRIEF", length = 500)
	private String brief;
	@Column(name = "CONTENT", length = 1024)
	private String content;
	@Column(name = "NEWSDATE")
	private Date currentDate;

	public News() {

	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getBrief() {
		return brief;
	}

	public String getContent() {
		return content;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brief == null) ? 0 : brief.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result
				+ ((currentDate == null) ? 0 : currentDate.hashCode());
		result = prime * result + id;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof News)) {
			return false;
		}
		News other = (News) obj;
		if (brief == null) {
			if (other.brief != null) {
				return false;
			}
		} else if (!brief.equals(other.brief)) {
			return false;
		}
		if (content == null) {
			if (other.content != null) {
				return false;
			}
		} else if (!content.equals(other.content)) {
			return false;
		}
		if (currentDate == null) {
			if (other.currentDate != null) {
				return false;
			}
		} else if (!currentDate.equals(other.currentDate)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}

	public News clone() throws CloneNotSupportedException {
		News obj = (News) super.clone();
		return obj;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder().append(getClass()).append(
				"\n");
		builder.append("NewsId: ").append(id).append("\n");
		builder.append("NewsDate: ").append(currentDate).append("\n");
		builder.append("Title: ").append(title).append("\n");
		builder.append("Brief: ").append(brief).append("\n");
		builder.append("Content: ").append(content).append("\n");
		return builder.toString();
	}
}
