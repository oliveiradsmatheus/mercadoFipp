<template>
    <div class="container my-5">
        <div class="text-center mb-4">
            <h1 class="display-4 fw-bold">Anúncios</h1>
            <p class="text-muted">Confira os melhores anúncios do Mercado FIPP.</p>
            <p v-if="this.anuncios">{{ this.anuncios.length }} resultados.</p>
            <p v-if="idUsr && this.anuncios">Todos os anúncios de {{ this.anuncios[0].usuario.nome}}</p>
        </div>
        <div class="row g-4">
            <div class="d-flex justify-content-between">
                <router-link class="text-decoration-none" to="cadastros/anuncios">
                    <button class="btn btn-warning">Anuncie aqui!</button>
                </router-link>
                <form class="d-flex" role="search">
                    <input class="form-control me-2" type="search" placeholder="Buscar" aria-label="Buscar"/>
                    <button class="btn btn-outline-dark" type="submit">Buscar</button>
                </form>
            </div>
            <div
                v-for="anuncio in anuncios"
                :key="anuncio.id"
                class="col-12 col-sm-6 col-lg-4 d-flex"
            >
                <Card :anuncio="anuncio" class="w-100"/>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import Card from "@/components/layouts/Card.vue";

export default {
    name: "Anuncios",
    components: {
        Card
    },
    props: {
        idUsr: String
    },
    data() {
        return {
            anuncios: null
        }
    },
    methods: {
        carregarAnuncios() {
            console.log("id usuario: " + this.idUsr)
            let url = "http://localhost:8080/apis/anuncio";
            if (this.idUsr) {
                url += `/get-por-usuario/${this.idUsr}`
            }

            axios.get(url)
                .then(resposta => {
                    console.log(resposta);
                    this.anuncios = resposta.data;
                })
                .catch(erro => {
                    console.log(erro);
                    toast.error("Erro ao carregar anúncios!", {
                        autoClose: 2000
                    })
                })
        }
    },
    mounted() {
        this.carregarAnuncios();
    }
}

</script>

<style scoped>

</style>