<template>
    <div class="container mt-4 p-3 w-50">
        <h2>Faça uma pergunta ao vendedor</h2>
        <div class="card bg-body-tertiary mt-4">
            <div class="card-header bg-primary text-white">
                <strong>Pergunte ao vendedor!</strong>
            </div>
        <div class="card-body">
            <form @submit.prevent="this.gravar()">
                <div class="mb-3">
                    <textarea class="form-control" id="pergunta" v-model="texto" rows="7"
                              placeholder="Este item ainda está disponível?" required></textarea>
                </div>
                <button type="submit" class="btn btn-primary mx-2">Enviar pergunta</button>
                <router-link class="text-decoration-none" :to="`/anuncio/${this.id}`">
                    <button class="btn btn-primary mx-2">
                        Voltar
                    </button>
                </router-link>
            </form>
        </div></div>
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
            texto: ""
        }
    },
    methods: {
        gravar() {
            const url = `http://localhost:8080/apis/anuncio/add-pergunta/${this.id}/${this.texto}`

            if (this.texto.length > 0)
                axios.post(url)
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
        resetarFormulario() {
            this.texto = "";
        }
    }
}
</script>

<style scoped>

</style>