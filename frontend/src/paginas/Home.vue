<template>
    <div class="container w-100 mt-4">
        <h1>Anúncios recentes</h1>
        <div class="d-flex justify-content-around flex-wrap">
            <div v-for="anuncio in anuncios" class="card m-3 p-2" style="width: 23rem;">
                <Card :anuncio="anuncio"/>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import {toast} from "vue3-toastify";
import Card from "@/components/layouts/Card.vue";

export default {
    name: "Home",
    components: {Card},
    data() {
        return {
            anuncios: []
        }
    },
    methods: {
        carregarAnuncios() {
            const url = "http://localhost:8080/apis/anuncio";

            axios.get(url)
                .then(resposta => {
                    let lista = resposta.data;
                    for (let i = lista.length - 1, j = 0; i >= 0 && j < 5; i--, j++)
                        this.anuncios.push(lista[i]);
                })
                .catch(erro => {
                    console.log(erro);
                    toast.error("Erro ao carregar anúncios!", {
                        autoClose: 2000
                    });
                });
        }
    },
    mounted() {
        this.carregarAnuncios()
    }
}
</script>

<style scoped>
.card {
    background-color: var(--branco);
}

p, h5 {
    color: var(--preto);
}

button {
    color: var(--preto);
}

.carousel-caption {
    border-radius: 10px;
    background: linear-gradient(
        rgba(242, 242, 242, 0.8),
        rgba(242, 242, 242, 0.55)
    );
}
</style>