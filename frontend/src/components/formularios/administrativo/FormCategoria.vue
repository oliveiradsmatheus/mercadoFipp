<template>
    <div class="container mt-4">
        <h1>Cadastro de Categorias</h1><br>
        <div v-if="formOn">
            <div class="container border p-4">
                <form @submit.prevent="this.gravar()">
                    <div class="mb-3">
                        <label for="idcat" class="form-label">ID</label>
                        <input type="text" class="form-control" id="idcat" v-model="id" placeholder="Id da Categoria"
                               disabled>
                    </div>
                    <div class="mb-3">
                        <label for="name" class="form-label">Nome da Categoria</label>
                        <input type="text" class="form-control" id="name" v-model="nome"
                               placeholder="Nome da Categoria">
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
        </div>
        <div class="botaoForm">
            <button class="btn btn-primary" @click="mostrarForm(true)">Nova Categoria</button>
        </div>
        <div class="mt-4">
            <table class="table table-striped table-hover" id="categorias">
                <thead>
                    <tr>
                        <th scope="col" @click="ordenarId()">Id</th>
                        <th scope="col" @click="ordenarNome()">Nome</th>
                        <th scope="col" colspan="2">Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="categoria in this.categorias">
                        <td>{{ categoria.id }}</td>
                        <td>{{ categoria.nome }}</td>
                        <td class="acoes">
                            <button @click="this.alterar(categoria)" class="btn btn-warning">
                                <img src="../../../assets/icones/acoes/editar.svg" alt="">
                            </button>
                            <button @click="this.apagar(categoria.id)" class=" btn btn-danger">
                                <img src="../../../assets/icones/acoes/deletar.svg" alt="">
                            </button>
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
    name: 'FormCategoria',
    data() {
        return {
            id: 0,
            nome: "",
            formOn: false,
            modoEdicao: false,
            nomeOrdenado: false,
            idOrdenado: false,
            categorias: []
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
                            this.carregarCategorias();
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
                            this.carregarCategorias();
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
            const url = "http://localhost:8080/apis/categoria/" + id;
            if (window.confirm("Deseja realmente deletar a categoria " + id + "?"))
                axios.delete(url)
                    .then(resposta => {
                        console.log(resposta);
                        toast.success("Categoria removida com sucesso!", {
                            autoClose: 2000
                        });
                        this.carregarCategorias();
                    })
                    .catch(erro => {
                        console.log(erro);
                        let er = erro + "";
                        if (er.endsWith("400"))
                            toast.error("Erro ao remover categoria! Produtos cadastrados na categoria!", {
                                autoClose: 2000
                            });
                        else
                            toast.error("Erro ao remover categoria!", {
                                autoClose: 2000
                            });
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
        ordenarNome() {
            if (!this.nomeOrdenado)
                this.categorias.sort((a, b) => a.nome.localeCompare(b.nome));
            else
                this.categorias.sort((a, b) => b.nome.localeCompare(a.nome));
            this.nomeOrdenado = !this.nomeOrdenado;
            this.idOrdenado = false;
        },
        ordenarId() {
            if (!this.idOrdenado)
                this.categorias.sort((a, b) => a.id - b.id);
            else
                this.categorias.sort((a, b) => b.id - a.id);
            this.idOrdenado = !this.idOrdenado;
            this.nomeOrdenado = false;
        }
    },
    mounted() {
        this.carregarCategorias();
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
    justify-content: center;
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
