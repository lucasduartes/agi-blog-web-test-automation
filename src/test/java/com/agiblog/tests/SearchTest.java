package com.agiblog.tests;

import com.agiblog.pages.HomePage;
import com.agiblog.pages.SearchResultsPage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic("Blog do Agi")
@Feature("Pesquisa de artigos")
class SearchTest extends BaseTest {

    @Test
    @Disabled(
            "Known issue: o overlay de busca permanece oculto após clicar na lupa"
    )
    @DisplayName(
            "Deve exibir o campo de busca ao clicar na lupa"
    )
    void shouldDisplaySearchFieldWhenClickingSearchIcon() {

        HomePage homePage =
                new HomePage(driver);

        homePage.openSearch();

        assertTrue(
                homePage.isSearchOverlayVisible(),
                "O overlay de busca deveria ser exibido após clicar na lupa"
        );

        assertTrue(
                homePage.isSearchInputVisible(),
                "O campo de busca deveria estar visível após clicar na lupa"
        );
    }

    @Test
    @DisplayName(
            "Deve retornar artigos para um termo válido"
    )
    void shouldReturnArticlesForValidSearch() {

        String searchTerm = "cartão";

        SearchResultsPage resultsPage =
                new SearchResultsPage(driver);

        resultsPage.searchFor(searchTerm);

        List<String> titles =
                resultsPage.getResultTitles();

        assertFalse(
                titles.isEmpty(),
                "A pesquisa deveria retornar pelo menos um artigo"
        );


        assertTrue(
                resultsPage.containsSearchTerm(searchTerm),
                "A página deveria identificar o termo pesquisado"
        );
    }

    @Test
    @DisplayName(
            "Não deve retornar artigos para um termo inexistente"
    )
    void shouldReturnNoArticlesForNonexistentSearch() {

        String searchTerm =
                "zzqaatestenoresults987654";

        SearchResultsPage resultsPage =
                new SearchResultsPage(driver);

        resultsPage.searchFor(searchTerm);

        assertFalse(
                resultsPage.hasResults(),
                "A pesquisa não deveria retornar artigos para um termo inexistente"
        );

        assertTrue(
                resultsPage.containsSearchTerm(searchTerm),
                "A página deveria identificar o termo pesquisado"
        );
    }
}