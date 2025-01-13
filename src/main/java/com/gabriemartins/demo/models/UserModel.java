package com.gabriemartins.demo.models;

//Importação dos pacotes utilizados
import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity //Anotação onde defino a classe como tabela do banco de dados
@Table(name = UserModel.TABLE_NAME) //Anotação que defino o nome da tabela
public class UserModel {
    public static final String TABLE_NAME = "user"; //final é como const no JS e static informa que não preciso instanciar a classe para usar essa informação

    //Definição das colunas da tabela usuario

    @Id //Anotação para informar que é meu ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Anotação para inserir autoIncrement
    @Column(name = "userId", unique = true) //Anotação para informar o nome da tabela
    private Long userId;

    @Column(name = "userName", length = 100, nullable = true) //Além do nome, informo a quantidade de caracteres e se pode ser nulo, que no caso, não pode
    @NotNull //Informa que não pode ser nulo
    @NotEmpty //Informa que não pode ser vazio
    private String userName;

    @JsonProperty(access = Access.WRITE_ONLY) //Informa que o dado só pode ser lido mas nunca pode ser mostrado na tela
    @Column(name = "userPassword", length = 60, nullable = true)
    @NotBlank //Informa que não pode ser nulo e nem vazio
    private String userPassword;

    @Column(name = "userRegistration", length = 10, nullable = true)
    @NotBlank
    private String userRegistration;

    @Column(name = "userDateBirth")
    private Date userDateBirth;

    //Aqui indico a chave estrangeira
    @ManyToOne //Como é uma relação onde um muitos para um, aqui indico que haverá uma relação entre curso e usuário
    @JoinColumn(name = "userCourse", nullable = false)
    private CourseModel userCourse;

    @OneToMany(mappedBy = "reserveUser")
    private List<ReserveModel> reserve = new ArrayList<ReserveModel>();

    public UserModel() {} //Criação de um construtor vazio
    
    //Construção do contrutor
    public UserModel(Long userId, String userName, String userPassword, String userRegistration, Date userDateBirth, CourseModel userCourse) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userRegistration = userRegistration;
        this.userDateBirth = userDateBirth;
        this.userCourse = userCourse;
    }


    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRegistration() {
        return this.userRegistration;
    }

    public void setUserRegistration(String userRegistration) {
        this.userRegistration = userRegistration;
    }

    public Date getUserDateBirth() {
        return this.userDateBirth;
    }

    public void setUserDateBirth(Date userDateBirth) {
        this.userDateBirth = userDateBirth;
    }

    public CourseModel getUserCourse() {
        return this.userCourse;
    }

    public void setUserCourse(CourseModel userCourse) {
        this.userCourse = userCourse;
    }


    //Método para averiguar igualdade de dois objetos
    @Override
    public boolean equals(Object obj){
        if (obj == this) {
            return true;
        }

        if(!(obj instanceof UserModel)){
            return false;
        }

        UserModel user = (UserModel) obj;
        return Objects.equals(userId, user.userId) && Objects.equals(userName, user.userName) && Objects.equals(userPassword, user.userPassword) && Objects.equals(userCourse, user.userCourse) && Objects.equals(userRegistration, user.userRegistration) && Objects.equals(userDateBirth, user.userDateBirth);
    }

    //Método para gerar um código unico de identificação de um objeto
    @Override
    public int hashCode(){
        return Objects.hash(userId,
            userName,
            userPassword,
            userRegistration,
            userDateBirth,
            userCourse
        );
    }
}
