<template>
    <div>
        <h1>Cadastro de Categorias</h1><br>
        <div v-if="formOn">
            <form @submit.prevent="this.gravar()">
                <label for="idcat">ID</label>
                <input type="text" id="idcat" v-model="id" placeholder="Id da Categoria">
                <label for="name">Nome</label>
                <input type="text" id="name" v-model="nome" placeholder="Nome da Categoria">
                <input type="submit" value="Cadastrar">
            </form>
        </div>
        <div style="display: flex; justify-content: flex-end">
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
                            <button @click="this.alterar(cat.id)" class="alterar">
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
import axios from "axios";

export default {
    name: 'FormCategoria',
    props: {
        msg: String
    },
    data() {
        return {
            id: 0,
            nome: "",
            formOn: false,
            categorias: []
        }
    },
    methods: {
        mostrarForm(flag) {
            this.formOn = flag;
        },
        gravar() {
            const url = 'http://localhost:8080/apis/categoria';
            const data = {nome: this.nome};

            axios.post(url, data)
                .then(response => {
                    this.carregarDados();
                    console.log("Sucesso:", response.data);
                })
                .catch(error => {
                    alert("Erro: ", error);
                });
        },
        apagar() {
            alert("Apagando")
        },
        alterar() {
            alert("Alterando")
        },
        carregarDados() {
            axios.get("http://localhost:8080/apis/categoria")
                .then(response => {
                    this.categorias = response.data;
                })
                .catch(erro => {
                    alert("Erro: ", erro);
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

button {
    background-color: #145de6;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

.alterar {
    background-color: #efd361;
    color: black;
}

.excluir {
    background-color: #e55555;
}

input[type=submit]:hover {
    background-color: #649aff;
}

div {
    border-radius: 5px;
    background-color: #f2f2f2;
    padding: 20px;
}

#customers {
    font-family: Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

#customers td, #customers th {
    border: 1px solid #ddd;
    padding: 8px;
}

#customers tr:nth-child(even) {
    background-color: #f2f2f2;
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
