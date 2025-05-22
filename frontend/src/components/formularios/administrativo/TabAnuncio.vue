<template>
    <div class="container mt-4">
        <h1>Gerenciamento de Anúncios</h1>
        <div class="mt-5">
            <table class="table table-striped table-hover" id="categorias">
                <thead>
                    <tr>
                        <th scope="col" @click="this.ordenar('id')">Id</th>
                        <th scope="col" @click="this.ordenar('titulo')">Título</th>
                        <th scope="col" @click="this.ordenar('descricao')">Descrição</th>
                        <th scope="col" @click="this.ordenar('preco')">Preço R$</th>
                        <th scope="col" @click="this.ordenar('categoria.nome')">Categoria</th>
                        <th scope="col" @click="this.ordenar('usuario.nome')">Usuário</th>
                        <th scope="col" colspan="2">Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="anuncio in this.anuncios">
                        <td>{{ anuncio.id }}</td>
                        <td>{{ anuncio.titulo }}</td>
                        <td>{{ anuncio.descricao }}</td>
                        <td>{{ anuncio.preco.toFixed(2) }}</td>
                        <td>{{ anuncio.categoria.nome }}</td>
                        <td>{{ anuncio.usuario.nome }}</td>
                        <td>
                            <div class="acoes">
                                <button @click="this.apagar(anuncio.id)" class=" btn btn-danger">
                                    <img src="../../../assets/icones/acoes/deletar.svg" alt="">
                                </button>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <router-link class="text-decoration-none" to="/">
            <button class="btn btn-primary m-4 mb-2" type="button">Voltar</button>
        </router-link>
    </div>
</template>

<script>
import {toast} from "vue3-toastify";
import "vue3-toastify/dist/index.css"
import axios from "axios";

export default {
    name: 'TabAnuncio',
    data() {
        return {
            anuncios: [],
            categorias: [],
            usuarios: [],
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
        apagar(id) {
            const url = "http://localhost:8080/apis/anuncio/" + id;
            if (window.confirm("Deseja realmente deletar o anúncio " + id + "?"))
                axios.delete(url)
                    .then(resposta => {
                        console.log(resposta);
                        toast.success("Anúncio removido com sucesso!", {
                            autoClose: 2000
                        });
                        this.carregarAnuncios();
                    })
                    .catch(erro => {
                        console.log(erro);
                    })
        },
        carregarCategorias() {
            const url = "http://localhost:8080/apis/categoria";

            axios.get(url)
                .then(resposta => {
                    console.log(resposta);
                    this.categorias = resposta.data;
                })
                .catch(erro => {
                    console.log(erro);
                    toast.error("Erro ao carregar categorias!", {
                        autoClose: 2000
                    });
                });
        },
        carregarUsuarios() {
            const url = "http://localhost:8080/apis/usuario";

            axios.get(url)
                .then(resposta => {
                    console.log(resposta);
                    this.usuarios = resposta.data;
                })
                .catch(erro => {
                    console.log(erro);
                    toast.error("Erro ao carregar usuários!", {
                        autoClose: 2000
                    });
                });
        },
        carregarAnuncios() {
            const url = "http://localhost:8080/apis/anuncio";

            axios.get(url)
                .then(resposta => {
                    console.log(resposta);
                    this.anuncios = resposta.data;
                })
                .catch(erro => {
                    console.log(erro);
                    toast.error("Erro ao carregar anúncios!", {
                        autoClose: 2000
                    });
                });
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
        }
    },
    mounted() {
        this.carregarCategorias();
        this.carregarUsuarios();
        this.carregarAnuncios();
    }
}
</script>

<style scoped>
th:hover {
    cursor: pointer;
}

form > div > .btn {
    margin-right: 10px;
    margin-left: 10px;
}

.botoes {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    align-content: center;
}

.botaoForm {
    display: flex;
    justify-content: flex-end;
}

.acoes {
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 8px;
}

.acoes > .btn-warning {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 40px;
    height: 40px;
    margin: 0 5px;
}

.acoes > .btn-danger {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 40px;
    height: 40px;
    margin: 0 5px;
}

td, th {
    align-content: center;
}

table {
    border-radius: 10px;
    overflow: hidden;
    width: 100%;
}
</style>
