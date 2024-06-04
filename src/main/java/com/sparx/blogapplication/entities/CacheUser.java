package com.sparx.blogapplication.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="cacheUser")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CacheUser {
	@Id
	String userId;
	String userName;
	

}
