package ec.edu.ups.ppw.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import ec.edu.ups.ppw.model.enums.Role;

@Entity
@Table(name = "PW_USUARIOS")
public class Usuario {
	public Usuario() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment en base de datos
    @Column(name = "usu_id")
    private int id;

    @Column(name = "usu_nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "usu_apellido", length = 100, nullable = false)
    private String apellido;

    @Column(name = "usu_email", length = 100, unique = true, nullable = false)
    private String email;

    @Column(name = "usu_password", length = 200, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private Role rol = Role.USER; 

    @Column(name = "usu_created_at", nullable = false, updatable = false) // updatable=false protege la fecha de creación
    private LocalDateTime createdAt;

    @Column(name = "usu_updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ProgramadorDetalles detalles;
 // --- MÉTODOS DE CICLO DE VIDA (LIFECYCLE CALLBACKS) ---
 // Se ejecuta automáticamente ANTES de insertar en la base de datos
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        // Aseguramos que el rol tenga valor si venía nulo
        if(this.rol == null) {
            this.rol = Role.USER;
        }
    }

    // Se ejecuta automáticamente ANTES de actualizar un registro existente
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRol() {
		return rol;
	}

	public void setRol(Role rol) {
		this.rol = rol;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public ProgramadorDetalles getDetalles() {
		return detalles;
	}

	public void setDetalles(ProgramadorDetalles detalles) {
		this.detalles = detalles;
	}
    
}