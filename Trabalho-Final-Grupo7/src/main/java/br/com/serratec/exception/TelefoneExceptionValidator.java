package br.com.serratec.exception;

public class TelefoneExceptionValidator {

    

  
    public boolean isValid(String telefone) {
        
        String regex = "^(\\([1-9]{2}\\))?\\s?(?:[2-8]|9[0-9])[0-9]{3}(?:-?[0-9]{4})$";
;
        if (telefone.matches(regex));
        return true;
    }

   
	
	public String getMessage() {
		return "Erro: O telefone fornecido é inválido. Tente utilizar o formato: (xx) xxxxx-xxxx.";
	}
}
