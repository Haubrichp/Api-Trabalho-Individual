package br.com.serratec.enums;

public enum RoleName {


	    ROLE_USUARIO(0,"usuario"), ROLE_ADMIN(1,"admin");
	
	private Integer codigo;
	private String tipo;
	
	RoleName(Integer codigo, String tipo) {
			this.setCodigo(codigo);
			this.tipo=tipo;
		}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public static RoleName fromValue(String tipo) {
        for (RoleName roleName : RoleName.values()) {
            if (roleName.tipo.equals(tipo)) {
                return roleName;
            }
        }
        throw new IllegalArgumentException("Tipo de acesso não existe: " + tipo);
		
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	}




