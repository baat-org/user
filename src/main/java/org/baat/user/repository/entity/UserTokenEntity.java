package org.baat.user.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_token")
public class UserTokenEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private String userToken;

	public UserTokenEntity() {
	}

	public UserTokenEntity(final Long userId, final String userToken) {
		this.userId = userId;
		this.userToken = userToken;
	}

	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(final Long userId) {
		this.userId = userId;
	}


	@Column(name = "user_token")
	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(final String userToken) {
		this.userToken = userToken;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		final UserTokenEntity that = (UserTokenEntity) o;

		if (id != null ? !id.equals(that.id) : that.id != null)
			return false;
		if (userId != null ? !userId.equals(that.userId) : that.userId != null)
			return false;
		return userToken != null ? userToken.equals(that.userToken) : that.userToken == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (userId != null ? userId.hashCode() : 0);
		result = 31 * result + (userToken != null ? userToken.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("UserTokenEntity{");
		sb.append("id=").append(id);
		sb.append(", userId=").append(userId);
		sb.append(", userToken='").append(userToken).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
