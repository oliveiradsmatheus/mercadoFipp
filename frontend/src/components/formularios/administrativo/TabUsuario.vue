<template>
    <div class="container mt-4">
        <h1>Gerenciamento de Usuários</h1>
        <div class="mt-5">
            <router-link class="text-decoration-none" to="/">
                <button class="btn btn-secondary my-1 mb-3 d-flex justify-content-start" type="button">Voltar</button>
            </router-link>
            <table class="table table-striped table-hover" id="usuarios">
                <thead>
                    <tr>
                        <th scope="col" @click="this.ordenar('id')">Id</th>
                        <th scope="col" @click="this.ordenar('nivel')">Nível</th>
                        <th scope="col" @click="this.ordenar('nome')">Nome</th>
                        <th scope="col" colspan="2">Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="usuario in this.usuarios">
                        <td>{{ usuario.id }}</td>
                        <td>{{ usuario.nivel }}</td>
                        <td>{{ usuario.nome }}</td>
                        <td class="acoes">
                            <button @click="this.apagar(usuario.id)" class="btn btn-danger excluir">
                                <img src="../../../assets/icones/acoes/deletar.svg" alt="">
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
    name: "TabUsuario",
    data() {
        return {
            usuarios: [],
            ordenado: {
                id: false,
                nome: false,
                nivel: false
            }
        }
    },
    methods: {
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
        ordenar(campo) {
            this.ordenado[campo] = !this.ordenado[campo];
            if (typeof this.usuarios[0]?.[campo] === "number")
                this.ordenado[campo] ?
                    this.usuarios.sort((a, b) => b[campo] - a[campo]) :
                    this.usuarios.sort((a, b) => a[campo] - b[campo]);
            else
                this.ordenado[campo] ?
                    this.usuarios.sort((a, b) => b[campo].localeCompare(a[campo])) :
                    this.usuarios.sort((a, b) => a[campo].localeCompare(b[campo]));
            Object.keys(this.ordenado).forEach(c => {
                if (c !== campo) this.ordenado[c] = false;
            })
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

.botaoForm {
    display: flex;
    justify-content: flex-end;
}

.acoes {
    display: flex;
    justify-content: center;
}

.excluir {
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