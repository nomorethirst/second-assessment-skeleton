package com.example.assess2.objects;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "UserTable")
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private Timestamp joined;
	private Boolean active;
	
	@ManyToMany
	private List<User> following;
	
	@ManyToMany
	private List<User> followers;
	
	@OneToMany
	private List<Tweet> tweets;
	
	@ManyToMany
	private List<Tweet> mentions;
	
	@Embedded
	private Profile profile;
	
	@Embedded
	private Credentials credentials;

	public User() {
		
	}
	
	public User(Integer id, long time, Profile profile, Credentials credentials) {
		this.id = id;
		this.joined = new Timestamp(time);
		this.profile = profile;
		this.credentials = credentials;
		this.active = true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	public List<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

	public List<Tweet> getMentions() {
		return mentions;
	}

	public void setMentions(List<Tweet> mentions) {
		this.mentions = mentions;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<User> getFollowing() {
		return following.stream().filter(User::getActive).collect(Collectors.toList());
	}

	public void setFollowing(List<User> following) {
		this.following = following;
	}

	public List<User> getFollowers() {
		return followers.stream().filter(User::getActive).collect(Collectors.toList());
	}

	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Timestamp getJoined() {
		return joined;
	}

	public void setJoined(Timestamp joined) {
		this.joined = joined;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
	
	
}
