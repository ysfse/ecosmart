package ecosmart.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@NoArgsConstructor
@Getter
@Setter
//@EqualsAndHashCode(of = {"id"})
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// Attributs //
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long user_id;
    
    @NotBlank
    @Size(min=3, max = 50)
    private String firstName;

    @NotBlank
    @Size(min=3, max = 50)
    private String lastName;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthDate;

    private String email;

    @NotBlank
    @Size(min=3, max = 50)
    private String username;
    
	@Column(length = 100)
	private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", 
    	joinColumns = @JoinColumn(name = "user_id"), 
    	inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_address_id", referencedColumnName = "location_id")
    private Location address;

    @ManyToMany(mappedBy="passengers")
    private List<Carpool> carpools = new ArrayList<>();
    
    @OneToMany(targetEntity= Carpool.class, mappedBy="driver")
    private List<Carpool> OfferedCarpools = new ArrayList<>();
    
    @OneToMany(targetEntity= Command.class, mappedBy="user")
    @JsonIgnore
	private List<Command> commands = new ArrayList<>();

	public User(@NotBlank @Size(min = 3, max = 50) String firstName, @NotBlank @Size(min = 3, max = 50) String lastName,
			String email, @NotBlank @Size(min = 3, max = 50) String username, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		
	}

	public List<Command> getCommands() {
		return commands;
	}

	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Location getAddress() {
		return address;
	}

	public void setAddress(Location address) {
		this.address = address;
	}

	public List<Carpool> getCarpools() {
		return carpools;
	}

	public void setCarpools(List<Carpool> carpools) {
		this.carpools = carpools;
	}






}