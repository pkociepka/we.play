package pl.edu.agh.weplay.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by P on 25.10.2016.
 */
@Entity
@Table
public class PersistentToken implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String series;

    @Column(name = "token_value") @NotNull @JsonIgnore
    private String tokenValue;

    @Column(name = "token_date") @JsonIgnore
    private LocalDate tokenDate;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "user_agent")
    private String userAgent;

    @JsonIgnore
    @ManyToOne
    private User user;

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String value) {
        this.tokenValue = value;
    }

    public LocalDate getTokenDate() {
        return tokenDate;
    }

    public void setTokenDate(LocalDate tokenDate) {
        this.tokenDate = tokenDate;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String iAddress) {
        this.ipAddress = iAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersistentToken token = (PersistentToken) o;

        return series.equals(token.series);

    }

    @Override
    public int hashCode() {
        return series.hashCode();
    }
}
