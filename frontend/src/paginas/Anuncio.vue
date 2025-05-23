<template>
    <div class="container my-4 h-100 w-100 mb-5">
        <div v-if="this.anuncio !== null">
            <div class="row mt-3">
                <Carrossel :fotos="this.anuncio.fotos"/>
                <Informacoes :anuncio="this.anuncio"/>
            </div>
            <div>
                <Perguntas :perguntas="this.anuncio.perguntas" :idAnuncio="this.anuncio.id"/>
            </div>
        </div>
        <div v-else>
            <div class="alert alert-danger" role="alert">
                Erro ao carregar anúncio!
            </div>
        </div>
        <router-link class="text-decoration-none" :to="logado ? '/anuncios' : '/'">
            <button class="btn btn-secondary mb-2" type="button">Voltar</button>
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
            anuncio: null,
            logado: true
        }
    },
    methods: {
        carregarAnuncio() {
            const url = `http://localhost:8080/apis/anuncio/${this.id}`

            axios.get(url)
                .then((resposta) => {
                    this.anuncio = resposta.data;
                    if (this.anuncio)
                        document.title += ` - ${this.anuncio.titulo}`;
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
        if (localStorage.getItem('perguntaRealizada') === 'true') {
            toast.success('Sua pergunta foi enviada! Aguarde a resposta do vendedor!', {
                autoClose: 2000
            });
            localStorage.removeItem('perguntaRealizada')
        } else if (localStorage.getItem('respostaRealizada') === 'true') {
            toast.success('Sua resposta foi enviada! Aguarde o contato do comprador!', {
                autoClose: 2000
            });
            localStorage.removeItem('respostaRealizada')
        }
    }
}
</script>

<style scoped>

</style>