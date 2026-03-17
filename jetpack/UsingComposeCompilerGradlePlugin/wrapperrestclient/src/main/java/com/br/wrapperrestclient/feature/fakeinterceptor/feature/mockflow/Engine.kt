package com.experience.tutorial.flowlivedata.sa.feature.fakeinterceptor.feature.mockflow

/**
 * Criar uma feature que permita mockar todas as requisicoes feitas por um app ou módulo inteiro
 *
 * Requisitos funcionais
 * 1 - A biblioteca deve fornecer um modo de intercepacao (mock/simular) e um modo normal, onde ela simplesmente
 * nao intercepta a requisicao
 *
 * 2 - O usuário deve criar sempre uma configuracao que contenha uma lista de mocks para
 * as requisicoes que ele deseja mockar/simular
 *      - Exemplo A
 *      - Imagine um app que com 3 telas
 *          - Tela A tem requisicoes A1, A2 e A3
 *          - Tela B tem requisicoes B1, B2
 *          - Tela C tem requisicoes C1, C2, C3 e C4
 *      - O usuario deve ser capaz de criar uma configuracao C1 com os possiveis resultados paras
 *      requisicoes A1 ... An, B1 ... Bn e assim por diante. Deve poder inserir Cn configuracoes com
 *      as respostas para as requisicoes que ele quiser mockar
 *
 *      - Que fique claro que N configuracoes podem ser criadas, com as combinacoes que o usuario deseje
 *      - Cada combinacao deve ser identificaad por uma descricao
 *
 * 3 - A biblioteca deve ter uma resposta padrao para as requisicoes que forem feitas no modo de intercepcao
 *     - Pode ser um 404 com uma mensagem simples
 *     - Seguindo o exemplo A, se o usuário não definir a resposta mockada para alguma das requisicoes
 *     a biblioteca retorna um 404 com uma mensagem: "App está no modo mock e não foi definida nenhuma resposta"
 *
 * 4 - Fornecer uma forma para o usuario escolher qual configuracao o app deve usar.
 */
class Engine {
}