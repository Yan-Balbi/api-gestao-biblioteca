package edu.yan.gestaobiblioteca.dto.usuario;

public class LoginResposta {
    private String token;

    private long expiraEm;


	public long getExpiraEm() {
		return expiraEm;
	}
	public void setExpiraEm(long expiraEm) {
		this.expiraEm = expiraEm;
	}
	
    public String getToken() {
        return token;
    }
	public void setToken(String token) {
		this.token = token;
	}
    
    
}
