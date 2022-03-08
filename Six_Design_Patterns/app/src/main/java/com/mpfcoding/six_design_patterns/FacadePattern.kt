package com.mpfcoding.six_design_patterns

import retrofit2.http.GET

/**
 * O Desgin Pattern Facade fornece uma Interface de nível superior que facilita o uso de um conjunto de outras Interfaces.
 * Se sua Activity precisar de uma lista de repositórios do Github, ela poderá fazer a chamada para essa lista sem entender o
 * funcionamento interno. Além de manter seu código limpo e conciso, isso permite que você faça as alterações necessárias na
 * implementação da API sem causar impacto no resto do projeto.
 * O Retrofit da Square é uma biblioteca Android que ajuda a implementar o Design pattern de Facade. Você cria uma interface para
 * fornecer os dados da API para classes do cliente da seguinte forma:
 * O cliente simplesmente precisa chamar getHamburgers() para receber uma lista de respositórios no retorno de chamada. É claro e limpo.
 * Isso permite que você faça todos os tipos de personalizações abaixo sem afetar o cliente. Por exemplo, você pode especificar
 * um desserializador personalizado de JSON e sua Activity nem precisa entender o que está acontecendo:
 */

interface FacadePattern {

    @GET("hamburgers")
    suspend fun getHamburgers(): List<HamburguerBuilderPattern>

}