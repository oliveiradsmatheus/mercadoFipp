<template>
    <div class="container mt-5 justify- w-50">
        <h1 class="mb-3">Entre</h1>
        <form @submit.prevent="this.logar()" class="p-3">
            <div class="mb-3 form-group">
                <label for="usuario">Usuário</label>
                <input type="text" class="form-control" id="usuario" v-model="this.nome"
                       placeholder="Informe o Usuário">
            </div>
            <div class="mb-3 form-group">
                <label for="senha">Senha</label>
                <input type="password" class="form-control" id="senha" v-model="this.senha" placeholder="Senha">
                <small class="form-text text-muted">Nós nunca compartilhos os seus dados com ninguém.</small>
            </div>
            <button type="submit" class="btn btn-warning mt-2 mx-2">Entrar</button>
            <router-link class="text-decoration-none" to="/">
                <button class="btn btn-secondary mt-2 mx-2" type="button">Voltar</button>
            </router-link>
        </form>
        <div class="text-center mt-3">
            <p>
                Não tem uma conta?
                <router-link to="/criar-conta" class="btn-link">Crie sua conta aqui</router-link>
            </p>
        </div>
    </div>
</template>

<script>
import {toast} from "vue3-toastify";
import axios from "axios";

export default {
    name: "Login",
    data() {
        return {
            nome: "",
            senha: "",
            autenticacao: null
        }
    },
    methods: {
        logar() {
            const url = "http://localhost:8080/apis/usuario/logar";
            const data = new FormData();
            data.append("nome", this.nome);
            data.append("senha", this.senha);
            axios.post(url, data)
                .then(resposta => {
                    console.log(resposta);
                    this.autenticacao = resposta.data;
                    localStorage.setItem("usuario", JSON.stringify(this.autenticacao));
                    this.$router.push("/");
                })
                .catch(erro => {
                    console.log(erro);
                    toast.error("Erro ao tentar entrar, confira seu nome de usuário ou senha!", {
                        autoClose: 2000
                    });
                });
        }
    },
    mounted() {
        if(localStorage.getItem("usuario"))
            this.$router.push("/");
        if (localStorage.getItem("cadastroRealizado")) {
            toast.success("Seu cadastro realizado com sucesso!", {
                autoClose: 2000
            });
            localStorage.removeItem("cadastroRealizado");
        }
        document.body.style.overflow = "hidden";
    },
    unmounted() {
        document.body.style.overflow = "auto";
    }
}
</script>

<style scoped>

</style>