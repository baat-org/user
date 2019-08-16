package org.baat.user.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_info")
public class UserInfoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String fullName;
	private String avatarUrl;

	public UserInfoEntity() {
	}

	public UserInfoEntity(final String fullName, final String email, final String avatarUrl) {
		this.fullName = fullName;
		this.email = email;
		this.avatarUrl = avatarUrl;
	}

	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@Column(name = "full_name")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(final String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@Column(name = "avatar_url")
	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(final String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		final UserInfoEntity that = (UserInfoEntity) o;

		if (id != null ? !id.equals(that.id) : that.id != null)
			return false;
		if (email != null ? !email.equals(that.email) : that.email != null)
			return false;
		if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null)
			return false;
		return avatarUrl != null ? avatarUrl.equals(that.avatarUrl) : that.avatarUrl == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
		result = 31 * result + (avatarUrl != null ? avatarUrl.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("UserInfoEntity{");
		sb.append("id=").append(id);
		sb.append(", email='").append(email).append('\'');
		sb.append(", fullName='").append(fullName).append('\'');
		sb.append(", avatarUrl='").append(avatarUrl).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
