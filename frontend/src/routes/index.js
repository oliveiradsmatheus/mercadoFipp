import {createRouter, createWebHistory} from "vue-router";
import Home from "@/paginas/Home.vue"
import Sobre from "@/paginas/Sobre.vue"
import Login from "@/paginas/Login.vue";
import Categorias from "@/paginas/Categorias.vue";
import Usuarios from "@/paginas/Usuarios.vue";
import Anuncio from "@/paginas/Anuncio.vue";

const routes = [
    {
        path: "/",
        component: Home,
        meta: {
            title: "Início"
        }
    },
    {
        path: "/sobre",
        component: Sobre,
        meta: {
            title: "Sobre"
        }
    },
    {
        path: "/login",
        component: Login,
        meta: {
            title: "Entrar"
        }
    },
    {
        path: "/categorias",
        component: Categorias,
        meta: {
            title: "Cadastro de categorias"
        }
    },
    {
        path: "/usuarios",
        component: Usuarios,
        meta: {
            title: "Cadastro de usuários"
        }
    },
    {
        path: "/anuncio/:id",
        component: Anuncio,
        meta: {
            title: "Anúncio"
        }
    }
];

const router = createRouter({
    history: createWebHistory(), // Para conseguir voltar no navegador
    routes
});

// Aplicar títulos em cada rota.
router.beforeEach((to, from, next) => {
    const defaultTitle = "Mercado FIPP";
    document.title = to.meta.title || defaultTitle;
    next();
});

export default router;