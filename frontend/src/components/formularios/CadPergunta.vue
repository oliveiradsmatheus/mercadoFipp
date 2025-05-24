<template>
    <div class="container mt-4 p-3 w-50">
        <h2>Faça uma pergunta ao vendedor</h2>
        <div class="card bg-body-tertiary mt-4">
            <div class="card-header text-white">
                <strong>Pergunte ao vendedor!</strong>
            </div>
            <div class="card-body">
                <form @submit.prevent="this.gravar()">
                    <div class="mb-3">
                    <textarea class="form-control" id="pergunta" v-model="texto" rows="7"
                              placeholder="Este item ainda está disponível?" required></textarea>
                    </div>
                    <button type="submit" class="btn btn-warning mx-2">Enviar pergunta</button>
                    <router-link class="text-decoration-none" :to="`/anuncio/${this.id}`">
                        <button class="btn btn-secondary mx-2">
                            Voltar
                        </button>
                    </router-link>
                </form>
            </div>
        </div>
    </div>
</template>

<script>

import axios from "axios";
import {toast} from "vue3-toastify";

export default {
    name: "CadPergunta",
    data() {
        return {
            id: this.$route.params.id,
            usuario: null,
            anuncio: null,
            texto: ""
        }
    },
    methods: {
        gravar() {
            const url = `http://localhost:8080/apis/anuncio/add-pergunta/${this.id}`;
            const data = {
                pergunta: this.texto
            }

            if (this.texto.length > 0)
                axios.post(url, data, {
                    headers: {
                        Authorization: JSON.parse(localStorage.getItem("usuario")).token
                    }
                })
                    .then(resposta => {
                        console.log(resposta);
                        localStorage.setItem("perguntaRealizada", "true");
                        this.$router.push(`/anuncio/${this.id}`);
                    })
                    .catch(erro => {
                        console.log(erro);
                        toast.error("Erro ao enviar pergunta!", {
                            autoClose: 2000
                        });
                        this.resetarFormulario();
                    });
        },
        recuperarAnuncio() {
            const url = `http://localhost:8080/apis/anuncio/${this.id}`;
            axios.get(url)
                .then(resposta => {
                    console.log(resposta);
                    this.anuncio = resposta.data;
                    if (this.usuario && this.usuario.id === this.anuncio.usuario.id)
                        this.$router.push("/nao-autorizado");
                })
                .catch(erro => {
                    console.log(erro);
                })
        },
        resetarFormulario() {
            this.texto = "";
        }
    },
    mounted() {
        this.usuario = JSON.parse(localStorage.getItem("usuario"));
        this.recuperarAnuncio();
    }
}
</script>

<style scoped>

</style>