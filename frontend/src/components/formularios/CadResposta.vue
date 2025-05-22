<template>
    <div class="container mt-4 p-3 w-50">
        <h2>Responda à pergunta</h2>
        <div class="card bg-body-tertiary mt-4">
            <div class="card-header bg-primary text-white">
                <strong>Pergunta do comprador</strong>
            </div>
            <div class="card-body">
                <p class="mb-3"><strong class="text-primary">Comprador:</strong> {{ texto }}</p>
                <form @submit.prevent="this.gravar()">
                    <div class="mb-3">
                        <label for="resposta" class="form-label">Sua resposta:</label>
                        <textarea class="form-control" id="resposta" rows="7" v-model="resposta"
                                  placeholder="Digite sua resposta para o comprador..." required></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary mx-2">Enviar Resposta</button>
                    <router-link class="text-decoration-none" :to="`/anuncio/${this.idAnuncio}`">
                        <button class="btn btn-primary mx-2">
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
    name: "FormResposta",
    data() {
        return {
            idAnuncio: this.$route.params.idA,
            idPergunta: this.$route.params.idP,
            texto: this.$route.params.per,
            resposta: ""
        }
    },
    methods: {
        gravar() {
            const url = `http://localhost:8080/apis/anuncio/add-resposta/${this.idPergunta}/${this.resposta}`

            if (this.resposta.length > 0)
                axios.post(url)
                    .then(resposta => {
                        console.log(resposta);
                        localStorage.setItem("respostaRealizada", "true");
                        this.$router.push(`/anuncio/${this.idAnuncio}`);
                    })
                    .catch(erro => {
                        console.log(erro);
                        toast.error("Erro ao enviar pergunta!", {
                            autoClose: 2000
                        });
                        this.resetarFormulario();
                    });
        },
        resetarFormulario() {
            this.resposta = "";
        }
    }
}
</script>

<style scoped>

</style>