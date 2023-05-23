package entities;

public class ModeloCadastro {

    String nome;
    String ano;
    String telefone;
    String email;
    String github;
    String cidade;
    String formacao;
    String experiencia;
    String nivIngles;
    String conhecimentos;

	public ModeloCadastro() {

        this.nome = nome;
        this.ano = ano;
        this.telefone = telefone;
        this.email = email;
        this.github = github;
        this.cidade = cidade;
        this.formacao = formacao;
        this.experiencia = experiencia;
        this.nivIngles = nivIngles;
        this.conhecimentos = conhecimentos;

	}

    public String getNome() {
        return nome;
    }

    public String getAno() {
        return ano;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getGithub() {
        return github;
    }

    public String getCidade() {
        return cidade;
    }

    public String getFormacao() {
        return formacao;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public String getNivIngles() {
        return nivIngles;
    }

    public String getConhecimentos() {
        return conhecimentos;
    }

    // ----------------------------------------------------------------------------------------------------------------------------------------------------------

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public void setNivIngles(String nivIngles) {
        this.nivIngles = nivIngles;
    }

    public void setConhecimentos(String conhecimentos) {
        this.conhecimentos = conhecimentos;
    }

}
