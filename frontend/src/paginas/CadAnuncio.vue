<template>
    <div class="container mt-4 p-4">
        <div class="card shadow">
            <div class="card-header bg-primary text-white">
                <h4 class="mb-0">Cadastrar Anúncio</h4>
            </div>
            <div class="card-body bg-body-tertiary">
                <form @submit.prevent="this.gravarAnuncio()">
                    <div class="mb-3">
                        <label for="titulo" class="form-label">Título do Anúncio</label>
                        <input type="text" class="form-control" id="titulo" v-model="titulo"
                               placeholder="Ex: Bicicleta usada">
                    </div>
                    <div class="mb-3">
                        <label for="descricao" class="form-label">Descrição</label>
                        <textarea class="form-control" id="descricao" v-model="descricao" rows="4"
                                  placeholder="Descreva o produto..."></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="categoria" class="form-label">Categoria</label>
                        <select class="form-select" id="categoria" v-model="idCategoria">
                            <option selected disabled>Selecione uma categoria</option>
                            <option v-for="categoria in categorias" :key="categoria.id" :value="categoria.id">
                                {{ categoria.nome }}
                            </option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="data" class="form-label">Data do Anúncio</label>
                        <input type="date" v-model="data" class="form-control" id="data">
                    </div>
                    <label for="valor" class="form-label">Valor (R$)</label>
                    <div class="input-group mb-3">
                        <span class="input-group-text">R$</span>
                        <input type="number" class="form-control" id="valor" v-model="preco" placeholder="Ex: 100">
                        <span class="input-group-text">.00</span>
                    </div>
                    <div class="mb-3">
                        <label for="fotos" class="form-label">Fotos (até 3)</label>
                        <input type="file" class="form-control" id="fotos" accept="image/*" multiple/>
                        <div class="form-text">Você pode adicionar até 3 imagens.</div>
                    </div>
                    <div class="d-grid">
                        <button type="submit" class="btn btn-primary">Cadastrar Anúncio</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import {toast} from "vue3-toastify";

export default {
    name: "CadAnuncio",
    data() {
        return {
            titulo: "",
            data: null,
            descricao: "",
            preco: "",
            idCategoria: 0,
            usuario: null,
            perguntas: [],
            fotos: [],
            categorias: []
        }
    },
    methods: {
        buscarCategorias() {
            const url = "http://localhost:8080/apis/categoria";
            axios.get(url)
                .then(resposta => {
                    this.categorias = resposta.data;
                })
                .catch(erro => {
                    toast.error("Erro ao carregar categorias!", {
                        autoClose: 2000
                    });
                });
        },
        gravarAnuncio() {
            const url = "http://localhost:8080/apis/anuncio";
            const data = {
                titulo: this.titulo,
                data: this.data,
                descricao: this.descricao,
                preco: this.preco,
                categoria: {
                    id: 2
                },
                usuario: {
                    id: 2
                },
                perguntas: [],
                fotos: [],
            };
            axios.post(url, data)
                .then(resposta => {
                    toast.success("Anúncio gravado com sucesso!", {
                        autoClose: 2000
                    });
                })
                .catch(erro => {
                    toast.error("Erro ao gravar anúncio! " + erro, {
                        autoClose: 2000
                    });
                });
        }
    },
    mounted() {
        this.buscarCategorias()
    }
}
</script>

<style scoped>
</style>