package org.baat.user.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Arrays;

@Entity
@Table(name = "user_credentials")
public class UserCredentialsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long userId;
	private String userName;
	private byte[] salt;
	private byte[] passwordHash;

	public UserCredentialsEntity() {
	}

	public UserCredentialsEntity(final Long userId, final String userName, final byte[] salt, final byte[] passwordHash) {
		this.userId = userId;
		this.userName = userName;
		this.salt = salt;
		this.passwordHash = passwordHash;
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


	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

	@Column(name = "salt")
	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(final byte[] salt) {
		this.salt = salt;
	}

	@Column(name = "password_hash")
	public byte[] getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(final byte[] passwordHash) {
		this.passwordHash = passwordHash;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		final UserCredentialsEntity that = (UserCredentialsEntity) o;

		if (id != null ? !id.equals(that.id) : that.id != null)
			return false;
		if (userId != null ? !userId.equals(that.userId) : that.userId != null)
			return false;
		if (userName != null ? !userName.equals(that.userName) : that.userName != null)
			return false;
		if (!Arrays.equals(salt, that.salt))
			return false;
		return Arrays.equals(passwordHash, that.passwordHash);
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (userId != null ? userId.hashCode() : 0);
		result = 31 * result + (userName != null ? userName.hashCode() : 0);
		result = 31 * result + Arrays.hashCode(salt);
		result = 31 * result + Arrays.hashCode(passwordHash);
		return result;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("UserCredentialsEntity{");
		sb.append("id=").append(id);
		sb.append(", userId=").append(userId);
		sb.append(", userName='").append(userName).append('\'');
		sb.append(", salt=").append(Arrays.toString(salt));
		sb.append(", passwordHash=").append(Arrays.toString(passwordHash));
		sb.append('}');
		return sb.toString();
	}
}
