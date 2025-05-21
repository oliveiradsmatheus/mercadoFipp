import {createRouter, createWebHistory} from "vue-router";
import Home from "@/paginas/Home.vue"
import Sobre from "@/paginas/Sobre.vue"
import Login from "@/paginas/Login.vue";
import Categorias from "@/paginas/Categorias.vue";
import Usuarios from "@/paginas/Usuarios.vue";
import Anuncio from "@/paginas/Anuncio.vue";
import CadAnuncio from "@/paginas/CadAnuncio.vue";
import Erro from "@/paginas/Erro.vue";

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
        path: "/cadastros/anuncios",
        component: CadAnuncio,
        meta: {
            title: "Cadastro de anúncios"
        }
    },
    {
        path: "/cadastros/categorias",
        component: Categorias,
        meta: {
            title: "Cadastro de categorias"
        }
    },
    {
        path: "/cadastros/usuarios",
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
    },
    {
        path: '/erro',
        component: Erro,
        meta: {
            title: "Erro"
        }
    },
    {
        path: '/:pathMatch(.*)*',
        redirect: '/erro'
    }
];

const router = createRouter({
    history: createWebHistory(), // Para conseguir voltar no navegador
    routes
});

// Aplicar títulos nas rota.
router.beforeEach((to, from, next) => {
    const defaultTitle = "Mercado FIPP";
    document.title = to.meta.title || defaultTitle;
    next();
});

export default router;