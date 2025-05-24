import {createRouter, createWebHistory} from "vue-router";
import Home from "@/paginas/Home.vue"
import Sobre from "@/paginas/Sobre.vue"
import Login from "@/paginas/Login.vue";
import CrudCategorias from "@/paginas/administrativo/CrudCategorias.vue";
import CrudUsuarios from "@/paginas/administrativo/CrudUsuarios.vue";
import Anuncio from "@/paginas/Anuncio.vue";
import CadAnuncio from "@/components/formularios/CadAnuncio.vue";
import Erro from "@/paginas/Erro.vue";
import CriarConta from "@/paginas/CriarConta.vue";
import CadPergunta from "@/components/formularios/CadPergunta.vue";
import CadResposta from "@/components/formularios/CadResposta.vue";
import CrudAnuncios from "@/paginas/administrativo/CrudAnuncios.vue";
import Integrantes from "@/paginas/Integrantes.vue";
import Anuncios from "@/paginas/Anuncios.vue";
import NaoAutorizado from "@/paginas/NaoAutorizado.vue";

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
        path: "/integrantes",
        component: Integrantes,
        meta: {
            title: "Integrantes"
        }
    },
    {
        path: "/criar-conta",
        component: CriarConta,
        meta: {
            title: "Cadastre-se no Mercado FIPP"
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
        path: '/nao-autorizado',
        component: NaoAutorizado,
        meta: {
            title: "Acesso negado!"
        }
    },
    {
        path: "/anuncio/:id",
        component: Anuncio,
        meta: {
            title: "Anúncio",
            requiresAuth: true,
            requiresAdmin: false
        }
    },
    {
        path: "/anuncios",
        component: Anuncios,
        meta: {
            title: "Todos os anúncios",
            requiresAuth: true,
            requiresAdmin: false
        }
    },
    {
        path: "/anuncios/usuario/:idUsr",
        component: Anuncios,
        props: true,
        meta: {
            title: "Todos os anúncios",
            requiresAuth: true,
            requiresAdmin: false
        }
    },
    {
        path: "/anuncios/filtro/:filtro",
        component: Anuncios,
        props: true,
        meta: {
            title: "Todos os anúncios",
            requiresAuth: true,
            requiresAdmin: false
        }
    },
    {
        path: "/perguntar/:id",
        component: CadPergunta,
        meta: {
            title: "Faça sua pergunta!",
            requiresAuth: true,
            requiresAdmin: false
        }
    },
    {
        path: "/responder/:idA/:idP/:per",
        component: CadResposta,
        meta: {
            title: "Responda às perguntas do seu anúncio!",
            requiresAuth: true,
            requiresAdmin: false
        }
    },
    {
        path: "/cadastros/anuncios",
        component: CadAnuncio,
        meta: {
            title: "Cadastro de anúncios",
            requiresAuth: true,
            requiresAdmin: false
        }
    },
    {
        path: "/adm/anuncios",
        component: CrudAnuncios,
        meta: {
            title: "Painel administrativo - Anúncios",
            requiresAuth: true,
            requiresAdmin: true
        }
    },
    {
        path: "/adm/categorias",
        component: CrudCategorias,
        meta: {
            title: "Painel administrativo - Categorias",
            requiresAuth: true,
            requiresAdmin: true
        }
    },
    {
        path: "/adm/usuarios",
        component: CrudUsuarios,
        meta: {
            title: "Painel administrativo - Usuários",
            requiresAuth: true,
            requiresAdmin: true
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
    const titulo = "Mercado FIPP";
    document.title = to.meta.title || titulo;
    const usuario = JSON.parse(localStorage.getItem("usuario"));
    if (to.meta.requiresAuth && !usuario)
        return next("/login");
    if (to.meta.requiresAdmin && (!usuario || usuario.nivel !== "0"))
        return next("/nao-autorizado");
    next();
});

export default router;