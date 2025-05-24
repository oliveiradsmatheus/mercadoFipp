<template>
    <div class="container my-5">
        <div class="text-center mb-4">
            <h1 class="display-4 fw-bold">Anúncios</h1>
            <p class="text-muted">Confira os melhores anúncios do Mercado FIPP.</p>
            <p v-if="this.anuncios">{{ this.anuncios.length }} resultados.</p>
            <p v-if="this.idUsuario && this.anuncios">Todos os anúncios de {{ this.anuncios[0].usuario.nome }}</p>
        </div>
        <div class="row g-4">
            <div class="d-flex justify-content-between">
                <router-link class="text-decoration-none" to="/cadastros/anuncios">
                    <button class="btn btn-warning">Anuncie aqui!</button>
                </router-link>
                <form class="d-flex" role="search">
                    <input class="form-control me-2" type="search" v-model="this.busca" placeholder="Buscar"
                           aria-label="Buscar" required/>
                    <router-link :to="this.busca.length > 0 ? `/anuncios/filtro/${busca}` : '/anuncios'">
                        <button class="btn btn-outline-dark" @click="carregarAnuncios()">Buscar</button>
                    </router-link>
                </form>
            </div>
            <p class="mt-3 mb-1 d-flex justify-content-start text m-2">Ordenar por:</p>
            <div class="mt-1 d-flex justify-content-start">
                <div class="btn-group" role="group" aria-label="Basic example">
                    <button type="button" class="btn btn-dark" @click="this.ordenar('titulo')">Título</button>
                    <button type="button" class="btn btn-dark" @click="this.ordenar('descricao')">Descrição</button>
                    <button type="button" class="btn btn-dark" @click="this.ordenar('preco')">Preço</button>
                    <button type="button" class="btn btn-dark" @click="this.ordenar('categoria.nome')">Categoria
                    </button>
                </div>
            </div>
            <div v-for="anuncio in anuncios" :key="anuncio.id" class="col-12 col-sm-6 col-lg-4 d-flex">
                <Card :anuncio="anuncio" class="w-100"/>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import {toast} from "vue3-toastify";
import Card from "@/components/layouts/Card.vue";

export default {
    name: "Anuncios",
    components: {
        Card
    },
    props: {
        idUsr: String,
        filtro: String
    },
    data() {
        return {
            anuncios: null,
            idUsuario: "",
            busca: "",
            ordenado: {
                id: false,
                titulo: false,
                descricao: false,
                preco: false,
                'categoria.nome': false,
                'usuario.nome': false
            }
        }
    },
    methods: {
        carregarAnuncios() {
            console.log(this.idUsr);
            console.log(this.filtro);
            let url = "http://localhost:8080/apis/anuncio";
            if (this.idUsuario)
                url += `/get-por-usuario/${this.idUsuario}`
            else if (this.busca.length > 0)
                url += `/get-anuncios/${this.busca}`

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
                });
            this.limparProps();
        },
        ordenar(campo) {
            this.ordenado[campo] = !this.ordenado[campo];
            const valorDoCampo = (obj, caminho) =>
                caminho.split('.').reduce((acc, part) => acc && acc[part], obj);
            this.anuncios.sort((a, b) => {
                const valorA = valorDoCampo(a, campo);
                const valorB = valorDoCampo(b, campo);
                if (typeof valorA === 'number' && typeof valorB === 'number')
                    return this.ordenado[campo] ? valorA - valorB : valorB - valorA;
                return this.ordenado[campo]
                    ? String(valorA).localeCompare(String(valorB))
                    : String(valorB).localeCompare(String(valorA));
            });
            Object.keys(this.ordenado).forEach(c => {
                if (c !== campo) this.ordenado[c] = false;
            });
        },
        limparProps() {
            this.busca = "";
            this.idUsuario = "";
        }
    },
    mounted() {
        if (this.idUsr)
            this.idUsuario = this.idUsr;
        this.carregarAnuncios();
    }
}

</script>

<style scoped>

</style>