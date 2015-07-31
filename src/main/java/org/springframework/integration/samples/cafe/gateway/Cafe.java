///////////////////////////////////////////////////////////////////////////////
//
// Ce fichier fait partie du projet Synergie - (C) Copyright 2014
// Tous droits réservés à L'Agence de Services et de Paiement (ASP).
//
// Tout ou partie de Synergie ne peut être copié et/ou distribué
// sans l'accord formel de l'ASP.
//
///////////////////////////////////////////////////////////////////////////////
package org.springframework.integration.samples.cafe.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.samples.cafe.business.Order;

/**
 * The entry point for the Cafe Demo. The demo's main() method invokes the '<code>placeOrder</code>' method on a
 * generated MessagingGateway proxy. The gateway then passes the {@link Order} as the payload of a
 * {@link org.springframework.integration.Message} to the configured <em>requestChannel</em>. The channel ('orders') is
 * defined in the 'cafeDemo.xml' file.
 *
 * @author Mark Fisher
 */
public interface Cafe {
    @Gateway(requestChannel = "orders")
    void placeOrder(Order order);
}
