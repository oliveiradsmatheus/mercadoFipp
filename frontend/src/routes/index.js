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
        path: "/anuncios",
        component: Anuncios,
        meta: {
            title: "Todos os anúncios"
        }
    },
    {
        path: "/anuncios/usuario/:idUsr",
        component: Anuncios,
        props: true,
        meta: {
            title: "Todos os anúncios"
        }
    },
    {
        path: "/anuncios/filtro/:filtro",
        component: Anuncios,
        props: true,
        meta: {
            title: "Todos os anúncios"
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
        path: "/login",
        component: Login,
        meta: {
            title: "Entrar"
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
        path: "/perguntar/:id",
        component: CadPergunta,
        meta: {
            title: "Faça sua pergunta!"
        }
    },
    {
        path: "/responder/:idA/:idP/:per",
        component: CadResposta,
        meta: {
            title: "Responda às perguntas do seu anúncio!"
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
        path: "/adm/anuncios",
        component: CrudAnuncios,
        meta: {
            title: "Painel administrativo - Anúncios"
        }
    },
    {
        path: "/adm/categorias",
        component: CrudCategorias,
        meta: {
            title: "Painel administrativo - Categorias"
        }
    },
    {
        path: "/adm/usuarios",
        component: CrudUsuarios,
        meta: {
            title: "Painel administrativo - Usuários"
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