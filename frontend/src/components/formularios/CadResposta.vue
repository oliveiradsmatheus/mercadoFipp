<template>
    <div class="container mt-4 p-3 w-50">
        <h2>Responda à pergunta</h2>
        <div class="card bg-body-tertiary mt-4">
            <div class="card-header text-white">
                <strong>Pergunta do comprador</strong>
            </div>
            <div class="card-body">
                <p class="mb-3"><strong class="text-dark">Comprador:</strong> {{ texto }}</p>
                <form @submit.prevent="this.gravar()">
                    <div class="mb-3">
                        <label for="resposta" class="form-label">Sua resposta:</label>
                        <textarea class="form-control" id="resposta" rows="7" v-model="resposta"
                                  placeholder="Digite sua resposta para o comprador..." required></textarea>
                    </div>
                    <button type="submit" class="btn btn-warning mx-2">Enviar Resposta</button>
                    <router-link class="text-decoration-none" :to="`/anuncio/${this.idAnuncio}`">
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
    name: "CadResposta",
    data() {
        return {
            idAnuncio: this.$route.params.idA,
            idPergunta: this.$route.params.idP,
            texto: "",
            usuario: null,
            anuncio: null,
            resposta: ""
        }
    },
    methods: {
        gravar() {
            const url = `http://localhost:8080/apis/anuncio/add-resposta/${this.idPergunta}`
            const data = {
                resposta: this.resposta
            }

            if (this.resposta.length > 0)
                axios.post(url, data, {
                    headers: {
                        Authorization: JSON.parse(localStorage.getItem("usuario")).token
                    }
                })
                    .then(resposta => {
                        console.log(resposta);
                        localStorage.setItem("respostaRealizada", "true");
                        this.$router.push(`/anuncio/${this.idAnuncio}`);
                    })
                    .catch(erro => {
                        console.log(erro);
                        toast.error("Erro ao enviar resposta!", {
                            autoClose: 2000
                        });
                        this.resetarFormulario();
                    });
        },
        recuperarAnuncio() {
            const url = `http://localhost:8080/apis/anuncio/${this.idAnuncio}`;
            axios.get(url)
                .then(resposta => {
                    console.log(resposta);
                    this.anuncio = resposta.data;
                    if (this.usuario && this.usuario.id !== this.anuncio.usuario.id)
                        this.$router.push("/nao-autorizado");
                    else
                        for (let pergunta of this.anuncio.perguntas)
                            if (pergunta.id === Number(this.idPergunta))
                                this.texto = pergunta.texto;
                })
                .catch(erro => {
                    console.log(erro);
                })
        },
        resetarFormulario() {
            this.resposta = "";
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