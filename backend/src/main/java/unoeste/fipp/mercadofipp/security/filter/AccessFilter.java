package unoeste.fipp.mercadofipp.security.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unoeste.fipp.mercadofipp.security.JWTTokenProvider;

import java.io.IOException;

public class AccessFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String token = req.getHeader("Authorization");
        String method = req.getMethod();
        String path = req.getRequestURI();

        if (method.equals("POST") && path.equals("/apis/user")) {
            // Permitir acesso público a POST /apis/user (cadastro de usuário)
            chain.doFilter(request, response);
        } else
            if (method.equals("GET") && path.startsWith("/apis/anuncio/get-anuncios")){
                // Permitir GET público para /apis/anuncio/get-anuncios/**
                chain.doFilter(request, response);
        } else
            if (token == null){
                // Para os demais casos, precisa de token
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                res.getWriter().write("Não autorizado: token não encontrado");
        } else
            if (!JWTTokenProvider.verifyToken(token)){
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                res.getWriter().write("Não autorizado: token inválido");
        } else {
            Claims claims = JWTTokenProvider.getAllClaimsFromToken(token);
            int nivel = Integer.parseInt(claims.get("nivel").toString());

            // Níveis esperados:
            // 1 = admin
            // 2 = usuário comum
            // 3 = guest (não autorizado na maior parte)

            // Regra para Categoria
            if (path.startsWith("/apis/categoria")) {
                if (method.equals("GET")) {
                    // GET categoria: adm e usuário comum
                    if (nivel == 1 || nivel == 2) {
                        chain.doFilter(request, response);
                        return;
                    } else {
                        forbidden(res);
                        return;
                    }
                } else if (method.equals("POST") || method.equals("PUT") || method.equals("DELETE")) {
                    // só adm pode POST, PUT, DELETE em categoria
                    if (nivel == 1) {
                        chain.doFilter(request, response);
                        return;
                    } else {
                        forbidden(res);
                        return;
                    }
                }
            }

            // Regra para Usuário
            if (path.startsWith("/apis/user")) {
                if (method.equals("GET")) {
                    // GET usuário (qualquer GET com /apis/user/**) - adm e usuário comum
                    if (nivel == 1 || nivel == 2) {
                        chain.doFilter(request, response);
                        return;
                    } else {
                        forbidden(res);
                        return;
                    }
                } else if (method.equals("DELETE")) {
                    // só adm pode deletar usuário
                    if (nivel == 1) {
                        chain.doFilter(request, response);
                        return;
                    } else {
                        forbidden(res);
                        return;
                    }
                } else if (method.equals("POST")) {
                    // POST usuário - liberado só para público (já tratado acima)
                    forbidden(res);
                    return;
                }
            }

            // Regra para Anúncio
            if (path.startsWith("/apis/anuncio")) {
                // GET público para get-anuncios liberado acima
                if (method.equals("GET")) {
                    // GET id, GET por usuário
                    if (path.matches("/apis/anuncio/get-por-usuario/\\d+") || path.matches("/apis/anuncio/\\d+")) {
                        if (nivel == 1 || nivel == 2) {
                            chain.doFilter(request, response);
                            return;
                        } else {
                            forbidden(res);
                            return;
                        }
                    }
                }

                // POST add-pergunta e add-resposta
                if (method.equals("POST")) {
                    if (path.matches("/apis/anuncio/add-pergunta/\\d+/[^/]+") || path.matches("/apis/anuncio/add-resposta/\\d+/[^/]+")) {
                        if (nivel == 1 || nivel == 2) {
                            chain.doFilter(request, response);
                            return;
                        } else {
                            forbidden(res);
                            return;
                        }
                    }

                    // POST anúncio normal (ex: criação)
                    if (path.equals("/apis/anuncio")) {
                        if (nivel == 1 || nivel == 2) {
                            chain.doFilter(request, response);
                            return;
                        } else {
                            forbidden(res);
                            return;
                        }
                    }
                }

                // DELETE anúncio - só adm
                if (method.equals("DELETE")) {
                    if (nivel == 1) {
                        chain.doFilter(request, response);
                        return;
                    } else {
                        forbidden(res);
                        return;
                    }
                }
            }

            // Caso nenhum critério casou, nega acesso
            forbidden(res);
        }
    }

    private void forbidden(HttpServletResponse res) throws IOException {
        res.setStatus(HttpServletResponse.SC_FORBIDDEN);
        res.getWriter().write("Acesso negado para seu nível de usuário");
    }
}
