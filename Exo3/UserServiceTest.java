package org.example;

import org.jetbrains.kotlin.protobuf.ServiceException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ModifiedUserServiceTest { // Renamed class

    @Mock
    private UtilisateurApi utilisateurApiMock1 = mock(UtilisateurApi.class);

    @Test
    void testCreerUtilisateur() throws ServiceException { // Modified method name
        Utilisateur utilisateur = new Utilisateur(456,"John", "Doe", "johndoe@email.com");
        
        doThrow(new ServiceException("Erreur lors de la création de l'utilisateur")).when(utilisateurApiMock1).creerUtilisateur(any());

        UserService userService1 = new UserService(utilisateurApiMock1);

        try {
            userService1.creerUtilisateur(utilisateur);
            fail("Une exception ServiceException aurait dû être lancée");
        } catch (ServiceException e) {
            verify(utilisateurApiMock1, times(1)).creerUtilisateur(utilisateur);
            assertEquals("Erreur lors de la création de l'utilisateur", e.getMessage());
        }

        verify(utilisateurApiMock1, times(1)).creerUtilisateur(utilisateur);
    }

    @Test
    void testValiderUtilisateur() throws ServiceException { // Modified method name
        Utilisateur utilisateur = new Utilisateur(456,"John", "Doe", "johndoe@email.com");
        UserService userService1 = new UserService(utilisateurApiMock1);

        doNothing().when(utilisateurApiMock1).creerUtilisateur(Mockito.any());

        userService1.creerUtilisateur(utilisateur);

        verify(utilisateurApiMock1).creerUtilisateur(utilisateur);
        verify(utilisateurApiMock1, never()).validerUtilisateur(utilisateur);
    }

    @Test
    void testVerifId() throws ServiceException { // Modified method name
        Utilisateur utilisateur = new Utilisateur(456,"John", "Doe", "johndoe@email.com");
        UserService userService1 = new UserService(utilisateurApiMock1);

        int idUtilisateur = 456;

        when(utilisateurApiMock1.getIdUtilisateur(utilisateur)).thenReturn(idUtilisateur);

        userService1.creerUtilisateur(utilisateur);

        assertEquals(idUtilisateur, userService1.getIdUtilisateur(utilisateur));
    }

    @Test
    void testTestArg() throws ServiceException { // Modified method name
        ArgumentCaptor<Utilisateur> argumentCaptor = ArgumentCaptor.forClass(Utilisateur.class);

        Utilisateur utilisateur = new Utilisateur(456,"John", "Doe", "johndoe@email.com");
        UserService userService = new UserService(utilisateurApiMock1);

        doNothing().when(utilisateurApiMock1).creerUtilisateur(argumentCaptor.capture());

        userService.creerUtilisateur(utilisateur);

        Utilisateur utilisateurCapture = argumentCaptor.getValue();

        assertEquals(456, utilisateurCapture.getId());
        assertEquals("John", utilisateurCapture.getNom());
        assertEquals("Doe", utilisateurCapture.getPrenom());
        assertEquals("johndoe@email.com", utilisateurCapture.getEmail());
    }
}
