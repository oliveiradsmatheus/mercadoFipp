<template>

    <div class="container mt-4">
        <h1>Cadastro de Usuários</h1><br>
        <div v-if="formOn">
            <form @submit.prevent="this.gravar()">
                <div class="mb-3">
                    <label for="idusr" class="form-label">ID</label>
                    <input type="text" class="form-control" id="idusr" v-model="id" placeholder="Id do Usuário"
                           disabled>
                </div>
                <div class="mb-3">
                    <label for="name" class="form-label">Nome do Usuário</label>
                    <input type="text" class="form-control" id="name" v-model="nome" placeholder="Nome do Usuário">
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
            <button class="btn btn-primary" @click="mostrarForm(true)">Novo Usuário</button>
        </div>
        <div class="mt-4">
            <table class="table table-striped table-hover" id="usuarios">
                <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Nome</th>
                        <th scope="col" colspan="2">Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="usuario in this.usuarios">
                        <td>{{ usuario.id }}</td>
                        <td>{{ usuario.nome }}</td>
                        <td class="acoes">
                            <button @click="this.alterar(usuario)" class="btn alterar">
                                <img src="../assets/icones/editar.svg" alt="">
                            </button>
                            <button @click="this.apagar(usuario.id)" class=" btn excluir">
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
    name: "FormUsuario",
    props: {
        msg: String
    },
    data() {
        return {
            id: 0,
            nome: "",
            formOn: false,
            modoEdicao: false,
            usuarios: []
        }
    },
    methods: {
        mostrarForm(flag) {
            this.formOn = flag;
        },
        gravar() {
            const url = 'http://localhost:8080/apis/usuario';
            if (this.modoEdicao) {
                const data = {id: this.id, nome: this.nome};

                if (this.nome.length > 0)
                    axios.put(url, data)
                        .then(resposta => {
                            this.carregarDados();
                            toast.success("Usuário alterado com sucesso!", {
                                autoClose: 2000
                            });
                            this.modoEdicao = false;
                            this.limparForm();
                        })
                        .catch(erro => {
                            toast.error("Erro ao alterar usuário!", {
                                autoClose: 2000
                            })
                        })
                else
                    toast.warning("Insira um nome para o usuário!", {
                        autoClose: 2000
                    });

            } else {
                const data = {nome: this.nome};

                if (this.nome.length > 0)
                    axios.post(url, data)
                        .then(resposta => {
                            this.carregarDados();
                            toast.success("Usuário gravado com sucesso!", {
                                autoClose: 2000
                            });
                            this.limparForm();
                        })
                        .catch(erro => {
                            toast.error("Erro ao gravar usuário!", {
                                autoClose: 2000
                            });
                        });
                else
                    toast.warning("Insira um nome para o usuário!", {
                        autoClose: 2000
                    });
            }
        },
        apagar(id) {
            const url = "http://localhost:8080/apis/usuario/" + id;
            if (window.confirm("Deseja realmente deletar o usuário " + id + "?"))
                axios.delete(url)
                    .then(resposta => {
                        toast.success("Usuário removido com sucesso!", {
                            autoClose: 2000
                        });
                        this.carregarDados();
                    })
                    .catch(erro => {
                        toast.error("Erro ao remover usuário!", {
                            autoClose: 2000
                        });
                    })
        },
        alterar(usuario) {
            this.mostrarForm(true);
            this.id = usuario.id;
            this.nome = usuario.nome;
            this.modoEdicao = true;
        },
        limparForm() {
            this.id = 0;
            this.nome = ""
            this.modoEdicao = false;
        },
        carregarDados() {
            const url = "http://localhost:8080/apis/usuario";

            axios.get(url)
                .then(resposta => {
                    this.usuarios = resposta.data;
                })
                .catch(erro => {
                    toast.error("Erro ao carregar usuários!", {
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

.alterar {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 40px;
    height: 40px;
    background-color: var(--amarelo);
    color: var(--preto);
    margin: 0 5px;
}

.excluir {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 40px;
    height: 40px;
    background-color: var(--vermelho);
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