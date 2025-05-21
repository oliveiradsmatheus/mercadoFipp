<template>
    <div class="container mt-4 h-100 w-100">
        <div v-if="this.anuncio !== null">
            <div class="row mt-3">
                <Carrossel :fotos="this.anuncio.fotos"/>
                <Informacoes :anuncio="this.anuncio"/>
            </div>
            <div>
                <Perguntas :perguntas="this.anuncio.perguntas"/>
            </div>
        </div>
        <div v-else>
            <div class="alert alert-danger" role="alert">
                Erro ao carregar anúncio!
            </div>
        </div>
        <router-link class="text-decoration-none" to="/">
            <button class="btn btn-primary mb-2" type="button">Voltar</button>
        </router-link>
    </div>
</template>

<script>
import {toast} from "vue3-toastify";
import axios from "axios";
import Carrossel from "@/components/layouts/Carrossel.vue";
import Informacoes from "@/components/layouts/Informacoes.vue";
import Perguntas from "@/components/layouts/Perguntas.vue";

export default {
    name: "Anuncio",
    components: {
        Perguntas,
        Informacoes,
        Carrossel
    },
    data() {
        return {
            id: this.$route.params.id,
            anuncio: null
        }
    },
    methods: {
        carregarAnuncio() {
            const url = `http://localhost:8080/apis/anuncio/${this.id}`

            axios.get(url)
                .then((resposta) => {
                    this.anuncio = resposta.data;
                })
                .catch(erro => {
                    console.log(erro);
                    toast.error("Erro ao alterar categoria!", {
                        autoClose: 2000
                    })
                })

        }
    },
    mounted() {
        this.carregarAnuncio();
    }
}
</script>

<style scoped>

</style>