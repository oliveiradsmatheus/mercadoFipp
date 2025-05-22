<template>
    <div class="container mt-4">
        <h1>Cadastro de Anúncios</h1><br>
        <div v-if="formOn">
            <div class="container border p-4">
                <form @submit.prevent="gravar">

                    <!-- Título -->
                    <div class="mb-3">
                        <label for="titulo" class="form-label">Título</label>
                        <input type="text" class="form-control" id="titulo" v-model="titulo"
                               placeholder="Título do anúncio">
                    </div>

                    <!-- Data -->
                    <div class="mb-3">
                        <label for="data" class="form-label">Data</label>
                        <input type="date" class="form-control" id="data" v-model="data">
                    </div>

                    <!-- Descrição -->
                    <div class="mb-3">
                        <label for="descricao" class="form-label">Descrição</label>
                        <textarea class="form-control" id="descricao" v-model="descricao" rows="3"
                                  placeholder="Descrição do produto"></textarea>
                    </div>

                    <!-- Preço -->
                    <div class="mb-3">
                        <label for="preco" class="form-label">Preço (R$)</label>
                        <input type="number" class="form-control" id="preco" v-model="preco" min="0" step="0.01">
                    </div>

                    <!-- Categoria -->
                    <div class="mb-3">
                        <label for="categoria" class="form-label">Categoria</label>
                        <select class="form-select" id="categoria" v-model="categoriaId">
                            <option disabled value="">Selecione uma categoria</option>
                            <option v-for="cat in categorias" :key="cat.cat_id" :value="cat.cat_id">
                                {{ cat.cat_name }}
                            </option>
                        </select>
                    </div>

                    <!-- Botões -->
                    <div class="botoes">
                        <input class="btn btn-primary" type="submit" value="Salvar">
                        <button class="btn btn-danger" type="button" @click="limparForm">Cancelar</button>
                    </div>

                </form>
            </div>
            <form @submit.prevent="this.gravar()">
                <div class="mb-3">
                    <label for="idcat" class="form-label">ID</label>
                    <input type="text" class="form-control" id="idcat" v-model="id" placeholder="Id da Categoria"
                           disabled>
                </div>
                <div class="mb-3">
                    <label for="name" class="form-label">Nome da Categoria</label>
                    <input type="text" class="form-control" id="name" v-model="nome" placeholder="Nome da Categoria">
                </div>
                <div class="botoes">
                    <div v-if="modoEdicao">
                        <input class="btn btn-primary" type="submit" value="Alterar">
                    </div>
                    <div v-else>
                        <input class="btn btn-primary" type="submit" value="Cadastrar">
                    </div>
                    <button class="btn btn-danger" type="button" @click="limparForm">Cancelar</button>
                </div>
            </form>
        </div>
        <div class="botaoForm">
            <button class="btn btn-primary" @click="mostrarForm(true)">Nova Categoria</button>
        </div>
        <div class="mt-4">
            <table class="table table-striped table-hover" id="categorias">
                <thead>
                    <tr>
                        <th scope="col" @click="ordenarId()">Id</th>
                        <th scope="col" @click="ordenarTitulo()">Título</th>
                        <th scope="col" @click="ordenarTitulo()">Descrição</th>
                        <th scope="col" @click="ordenarTitulo()">Preço R$</th>
                        <th scope="col" @click="ordenarTitulo()">Categoria</th>
                        <th scope="col" @click="ordenarTitulo()">Usuário</th>
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
                                <button @click="this.alterar(anuncio)" class="btn btn-warning">
                                    <img src="../../../assets/icones/acoes/editar.svg" alt="">
                                </button>
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
    name: 'FormAnuncio',
    data() {
        return {
            id: 0,
            nome: "",
            formOn: false,
            modoEdicao: false,
            tituloOrdenado: false,
            idOrdenado: false,
            anuncios: [],
            categorias: [],
            usuarios: []
        }
    },
    methods: {
        mostrarForm(flag) {
            this.formOn = flag;
        },
        gravar() {
            const url = 'http://localhost:8080/apis/categoria';
            if (this.modoEdicao) {
                const data = {id: this.id, nome: this.nome};

                if (this.nome.length > 0)
                    axios.put(url, data)
                        .then(resposta => {
                            console.log(resposta);
                            this.carregarAnuncios();
                            toast.success("Categoria alterada com sucesso!", {
                                autoClose: 2000
                            });
                            this.modoEdicao = false;
                            this.limparForm();
                        })
                        .catch(erro => {
                            console.log(erro);
                            toast.error("Erro ao alterar categoria!", {
                                autoClose: 2000
                            })
                        })
                else
                    toast.warning("Insira um nome para a categoria!", {
                        autoClose: 2000
                    });

            } else {
                const data = {nome: this.nome};

                if (this.nome.length > 0)
                    axios.post(url, data)
                        .then(resposta => {
                            console.log(resposta);
                            this.carregarAnuncios();
                            toast.success("Categoria gravada com sucesso!", {
                                autoClose: 2000
                            });
                            this.limparForm();
                        })
                        .catch(erro => {
                            console.log(erro);
                            toast.error("Erro ao gravar categoria!", {
                                autoClose: 2000
                            });
                        });
                else
                    toast.warning("Insira um nome para a categoria!", {
                        autoClose: 2000
                    });
            }
        },
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
        alterar(cat) {
            this.mostrarForm(true);
            this.id = cat.id;
            this.nome = cat.nome;
            this.modoEdicao = true;
        },
        limparForm() {
            this.id = 0;
            this.nome = ""
            this.modoEdicao = false;
            this.formOn = false;
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
        ordenarTitulo() {
            if (!this.tituloOrdenado)
                this.anuncios.sort((a, b) => a.titulo.localeCompare(b.titulo));
            else
                this.anuncios.sort((a, b) => b.titulo.localeCompare(a.titulo));
            this.tituloOrdenado = !this.tituloOrdenado;
            this.idOrdenado = false;
        },
        ordenarId() {
            if (!this.idOrdenado)
                this.anuncios.sort((a, b) => a.id - b.id);
            else
                this.anuncios.sort((a, b) => b.id - a.id);
            this.idOrdenado = !this.idOrdenado;
            this.tituloOrdenado = false;
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
