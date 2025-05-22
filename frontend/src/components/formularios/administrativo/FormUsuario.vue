<template>
    <div class="container mt-4">
        <h1>Cadastro de Usuários</h1><br>
        <div v-if="formOn">
            <div class="container border p-4">
                <form @submit.prevent="this.gravar()">
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="idusr" class="form-label">ID</label>
                            <input type="text" class="form-control" id="idusr" v-model="id" placeholder="Id do Usuário"
                                   disabled>
                        </div>
                        <div class="col-md-6">
                            <label for="nivel" class="form-label">Nível</label>
                            <input type="text" class="form-control" id="nivel" v-model="nivel"
                                   placeholder="Nível do Usuário">
                        </div>
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
        </div>
        <div class="botaoForm">
            <button class="btn btn-primary" @click="mostrarForm(true)">Novo Usuário</button>
        </div>
        <div class="mt-4">
            <table class="table table-striped table-hover" id="usuarios">
                <thead>
                    <tr>
                        <th scope="col" @click="ordenarId()">Id</th>
                        <th scope="col" @click="ordenarNivel()">Nível</th>
                        <th scope="col" @click="ordenarNome()">Nome</th>
                        <th scope="col" colspan="2">Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="usuario in this.usuarios">
                        <td>{{ usuario.id }}</td>
                        <td>{{ usuario.nivel }}</td>
                        <td>{{ usuario.nome }}</td>
                        <td class="acoes">
                            <button @click="this.alterar(usuario)" class="btn alterar">
                                <img src="../../../assets/icones/acoes/editar.svg" alt="">
                            </button>
                            <button @click="this.apagar(usuario.id)" class=" btn excluir">
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
    name: "FormUsuario",
    data() {
        return {
            id: 0,
            nome: "",
            nivel: 1,
            formOn: false,
            modoEdicao: false,
            idOrdenado: false,
            nomeOrdenado: false,
            nivelOrdenado: false,
            usuarios: []
        }
    },
    methods: {
        mostrarForm(flag) {
            this.formOn = flag;
        },
        gravar() {
            const url = "http://localhost:8080/apis/usuario";
            if (this.modoEdicao) {
                const data = {id: this.id, nome: this.nome, nivel: this.nivel};

                if (this.nome.length > 0)
                    axios.put(url, data)
                        .then(resposta => {
                            console.log(resposta);
                            this.carregarUsuarios();
                            toast.success("Usuário alterado com sucesso!", {
                                autoClose: 2000
                            });
                            this.modoEdicao = false;
                            this.limparForm();
                        })
                        .catch(erro => {
                            console.log(erro);
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
                            console.log(resposta);
                            this.carregarUsuarios();
                            toast.success("Usuário gravado com sucesso!", {
                                autoClose: 2000
                            });
                            this.limparForm();
                        })
                        .catch(erro => {
                            console.log(erro);
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
            const url = `http://localhost:8080/apis/usuario/${id}`;
            if (window.confirm("Deseja realmente deletar o usuário " + id + "?"))
                axios.delete(url)
                    .then(resposta => {
                        console.log(resposta);
                        toast.success("Usuário removido com sucesso!", {
                            autoClose: 2000
                        });
                        this.carregarUsuarios();
                    })
                    .catch(erro => {
                        console.log(erro);
                        let er = erro + "";
                        if (er.endsWith("400"))
                            toast.error("Erro ao remover usuário! O usuário possui anúncios ativos!", {
                                autoClose: 2000
                            });
                        else
                            toast.error("Erro ao remover usuário!", {
                                autoClose: 2000
                            });
                    })
        },
        alterar(usuario) {
            this.mostrarForm(true);
            this.id = usuario.id;
            this.nivel = usuario.nivel;
            this.nome = usuario.nome;
            this.modoEdicao = true;
        },
        limparForm() {
            this.id = 0;
            this.nome = "";
            this.nivel = 1;
            this.modoEdicao = false;
            this.formOn = false;
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
        ordenarNome() {
            if (!this.nomeOrdenado)
                this.usuarios.sort((a, b) => a.nome.localeCompare(b.nome));
            else
                this.usuarios.sort((a, b) => b.nome.localeCompare(a.nome));
            this.nomeOrdenado = !this.nomeOrdenado;
            this.idOrdenado = false;
            this.nivelOrdenado = false;
        },
        ordenarId() {
            if (!this.idOrdenado)
                this.usuarios.sort((a, b) => a.id - b.id);
            else
                this.usuarios.sort((a, b) => b.id - a.id);
            this.idOrdenado = !this.idOrdenado;
            this.nomeOrdenado = false;
            this.nivelOrdenado = false;
        },
        ordenarNivel() {
            if (!this.nivelOrdenado)
                this.usuarios.sort((a, b) => a.nivel - b.nivel);
            else
                this.usuarios.sort((a, b) => b.nivel - a.nivel);
            this.nivelOrdenado = !this.nivelOrdenado;
            this.nomeOrdenado = false;
            this.idOrdenado = false;
        }
    },
    mounted() {
        this.carregarUsuarios();
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