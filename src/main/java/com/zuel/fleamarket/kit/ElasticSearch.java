package com.zuel.fleamarket.kit;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ElasticSearch {
    TransportClient client;

    public ElasticSearch(){
        try {
            TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("106.15.251.188"), 9300));
            this.client = client;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
