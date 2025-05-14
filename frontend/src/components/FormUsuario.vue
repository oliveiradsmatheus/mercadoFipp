FormCategoria.vue<template>
    <div>
        <h1>Cadastro de Usuários</h1><br>
        <div v-if="formOn">
            <form @submit.prevent="this.gravar()">
                <label for="idcat">ID</label>
                <input type="text" id="idcat" v-model="id" placeholder="Id da Categoria" disabled>
                <label for="name">Nome</label>
                <input type="text" id="name" v-model="nome" placeholder="Nome da Categoria">
                <input type="submit" value="Cadastrar">
            </form>
            <button @click="limparForm">Cancelar</button>
        </div>
        <div class="botaoForm">
            <button @click="mostrarForm(true)">Nova Categoria</button>
        </div>
        <div>
            <table id="customers">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nome</th>
                        <th colspan="2">Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="cat in this.categorias">
                        <td>{{ cat.id }}</td>
                        <td>{{ cat.nome }}</td>
                        <td>
                            <button @click="this.alterar(cat)" class="alterar">
                                <img src="../assets/icones/editar.svg" alt="">
                            </button>
                        </td>
                        <td>
                            <button @click="this.apagar(cat.id)" class="excluir">
                                <img src="../assets/icones/deletar.svg" alt="">
                            </button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
import {toast} from "vue3-toastify";
import "vue3-toastify/dist/index.css"
import axios from "axios";

export default {
    name: 'FormUsuario',
    props: {
        msg: String
    },
    data() {
        return {
            id: 0,
            nome: "",
            formOn: false,
            modoEdicao: false,
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
                            this.carregarDados();
                            toast.success("Categoria alterada com sucessa!", {
                                autoClose: 2000
                            });
                            this.modoEdicao = false;
                            this.limparForm();
                        })
                        .catch(erro => {
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
                            this.carregarDados();
                            toast.success("Categoria gravada com sucesso!", {
                                autoClose: 2000
                            });
                        })
                        .catch(erro => {
                            toast.error("Erro ao gravar categoria: " + erro, {
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
                        toast.success("Categoria removida com sucesso!", {
                            autoClose: 2000
                        });
                        this.carregarDados();
                    })
                    .catch(erro => {
                        toast.error("Erro ao remover categoria: " + erro, {
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
        },
        carregarDados() {
            const url = "http://localhost:8080/apis/categoria";

            axios.get(url)
                .then(resposta => {
                    this.categorias = resposta.data;
                })
                .catch(erro => {
                    toast.error("Erro ao carregar categorias: " + erro, {
                        autoClose: 2000
                    });
                });
        }
    },
    mounted() {
        this.carregarDados();
    }
}
</script>

<style scoped>
input[type=text], select {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

input[type=submit] {
    width: 100%;
    background-color: #4182fb;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

input:focus {
    border-color: #86b7fe;
    outline: 0;
    box-shadow: 0 0 0 .25rem rgba(13, 110, 253, .25)
}

button {
    background-color: #145de6;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

.botaoForm {
    display: flex;
    justify-content: flex-end;
}

.alterar {
    display: flex;
    justify-content: center;
    width: 50px;
    height: 50px;
    background-color: var(--amarelo);
    color: var(--preto);
}

.excluir {
    display: flex;
    justify-content: center;
    width: 50px;
    height: 50px;
    background-color: var(--vermelho);
}

input[type=submit]:hover {
    background-color: #649aff;
}

div {
    border-radius: 5px;
    background-color: var(--branco);
    padding: 20px;
}

#customers {
    border-collapse: collapse;
    width: 100%;
}

#customers td, #customers th {
    border: 1px solid #ddd;
    padding: 8px;
}

#customers tr:nth-child(even) {
    background-color: var(--branco);
}

#customers tr:hover {
    background-color: #ddd;
}

#customers th {
    width: 1000px;
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: left;
    background-color: #4182fb;
    color: white;
}
</style>
