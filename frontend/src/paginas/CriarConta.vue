<template>
    <div class="container mt-5 w-50">
        <h1 class="mb-3">Criar Conta</h1>
        <form @submit.prevent="this.validar()" class="p-3">
            <div class="mb-3 form-group">
                <label for="usuario">Usuário</label>
                <input type="text" class="form-control" id="usuario" v-model="nome" placeholder="Informe o Usuário"
                       required>
            </div>
            <div class="mb-3 form-group">
                <label for="senha">Senha</label>
                <input type="password" class="form-control" id="senha" v-model="senha" placeholder="Senha" required>
            </div>
            <div class="mb-3 form-group">
                <label for="confirmarSenha">Confirmar Senha</label>
                <input type="password" class="form-control" id="confirmarSenha" v-model="confirmarSenha"
                       placeholder="Confirmar Senha" required>
            </div>
            <button type="submit" class="btn btn-warning mt-2 mx-3">Criar Conta</button>
            <router-link class="text-decoration-none" to="/">
                <button class="btn btn-secondary mt-2 mx-3" type="button">Voltar</button>
            </router-link>
        </form>
        <div class="text-center mt-3 align-items-center">
            <p>
                Já possui uma conta?
                <router-link to="/login" class="btn-link">Entre</router-link>
            </p>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import {toast} from "vue3-toastify";

export default {
    name: "CriarConta",
    data() {
        return {
            nome: "",
            senha: "",
            confirmarSenha: "",
            listaUsuarios: []
        }
    },
    methods: {
        resetarFormulario() {
            this.nome = "";
            this.senha = "";
            this.confirmarSenha = "";
        },
        validar() {
            if (this.senha === this.confirmarSenha) {
                const url = "http://localhost:8080/apis/usuario/get-nomes";

                axios.get(url)
                    .then(resposta => {
                        console.log(resposta);
                        this.listaUsuarios = resposta.data;
                        let i = 0;
                        while (i < this.listaUsuarios.length && this.nome !== this.listaUsuarios[i])
                            i++;
                        if (i === this.listaUsuarios.length)
                            this.cadastrar();
                        else {
                            toast.error("Nome de usuário já cadastrado!", {
                                autoClose: 2000
                            });
                            this.resetarFormulario();
                        }
                    })
                    .catch(erro => {
                        console.log(erro);
                    });
            }
        },
        cadastrar() {
            const url = "http://localhost:8080/apis/usuario"
            const data = {
                nome: this.nome,
                senha: this.senha,
                nivel: 1
            }
            axios.post(url, data)
                .then(resposta => {
                    console.log(resposta);
                    localStorage.setItem("cadastroRealizado", "true")
                    this.$router.push("/login");
                })
                .catch(erro => {
                    console.log(erro);
                    toast.error("Erro ao realizar o cadastro!", {
                        autoClose: 2000
                    });
                    this.resetarFormulario();
                });
        }
    },
    mounted() {
        if(localStorage.getItem("usuario"))
            this.$router.push("/");
    }
}
</script>

<style scoped>

</style>