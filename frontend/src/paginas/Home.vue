<template>
    <div class="container mt-5">
        <div class="text-center mb-4">
            <h1 class="display-4 fw-bold">Anúncios Recentes</h1>
            <p class="text-muted">Veja os anúncios mais recentes da nossa plataforma</p>
        </div>

        <div class="position-relative">
            <button
                @click="moverCarrossel('left')"
                class="btn btn-outline-warning position-absolute top-50 start-0 translate-middle-y"
                style="z-index: 2"
            >
                ‹
            </button>
            <div
                ref="carousel"
                class="d-flex overflow-auto gap-4 px-5 scroll-snap-x"
                style="scroll-behavior: smooth"
            >
                <div
                    v-for="anuncio in anuncios"
                    :key="anuncio.id"
                    class="card flex-shrink-0 shadow-sm border-0"
                    style="min-width: 352px; max-width: 352px"
                >
                    <Card :anuncio="anuncio"/>
                </div>
            </div>
            <button
                @click="moverCarrossel('right')"
                class="btn btn-outline-warning position-absolute top-50 end-0 translate-middle-y"
                style="z-index: 2"
            >
                ›
            </button>
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
                    console.log(resposta);
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
        },
        moverCarrossel(direction) {
            const container = this.$refs.carousel;
            const scrollAmount = 376;

            if (direction === 'left')
                container.scrollBy({left: -scrollAmount, behavior: 'smooth'});
            else
                container.scrollBy({left: scrollAmount, behavior: 'smooth'});

        }
    },
    mounted() {
        this.carregarAnuncios()
    }
}
</script>

<style scoped>
.scroll-snap-x::-webkit-scrollbar {
    display: none;
}

.btn-carousel-left {
    left: -40px; /* tira o botão para fora à esquerda */
    top: 50%;
    transform: translateY(-50%);
    z-index: 2;
}

.btn-carousel-right {
    right: -40px; /* tira o botão para fora à direita */
    top: 50%;
    transform: translateY(-50%);
    z-index: 2;
}
</style>