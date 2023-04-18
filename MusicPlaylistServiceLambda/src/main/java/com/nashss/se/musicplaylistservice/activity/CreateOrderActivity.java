//package com.nashss.se.musicplaylistservice.activity;
//
//import com.nashss.se.musicplaylistservice.activity.requests.CreatePlaylistRequest;
//import com.nashss.se.musicplaylistservice.activity.results.CreatePlaylistResult;
//import com.nashss.se.musicplaylistservice.converters.ModelConverter;
//import com.nashss.se.musicplaylistservice.dynamodb.OrderDao;
//import com.nashss.se.musicplaylistservice.dynamodb.models.Playlist;
//import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;
//import com.nashss.se.musicplaylistservice.models.PlaylistModel;
//import com.nashss.se.projectresources.music.playlist.servic.util.MusicPlaylistServiceUtils;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Set;
//
//public class CreateOrderActivity {
//    private final Logger log = LogManager.getLogger();
//    private final OrderDao orderDao;
//
//
//    public CreateOrderActivity(OrderDao orderDao) {
//        this.orderDao = orderDao;
//    }
//
//    public CreatePlaylistResult handleRequest(final CreateOrderRequest createOrderRequest) {
//        log.info("Received OrderRequest {}", createOrderRequest);
//
//        if (!MusicPlaylistServiceUtils.isValidString(createPlaylistRequest.getName())) {
//            throw new InvalidAttributeValueException("Playlist name [" + createPlaylistRequest.getName() +
//                    "] contains illegal characters");
//        }
//
//        if (!MusicPlaylistServiceUtils.isValidString(createPlaylistRequest.getCustomerId())) {
//            throw new InvalidAttributeValueException("Playlist customer ID [" + createPlaylistRequest.getCustomerId() +
//                    "] contains illegal characters");
//        }
//
//        Set<String> playlistTags = null;
//        if (createPlaylistRequest.getTags() != null) {
//            playlistTags = new HashSet<>(createPlaylistRequest.getTags());
//        }
//
//        Playlist newPlaylist = new Playlist();
//        newPlaylist.setId(MusicPlaylistServiceUtils.generatePlaylistId());
//        newPlaylist.setName(createPlaylistRequest.getName());
//        newPlaylist.setCustomerId(createPlaylistRequest.getCustomerId());
//        newPlaylist.setCustomerName(createPlaylistRequest.getCustomerName());
//        newPlaylist.setSongCount(0);
//        newPlaylist.setTags(playlistTags);
//        newPlaylist.setSongList(new ArrayList<>());
//
//        playlistDao.savePlaylist(newPlaylist);
//
//        PlaylistModel playlistModel = new ModelConverter().toPlaylistModel(newPlaylist);
//        return CreatePlaylistResult.builder()
//                .withPlaylist(playlistModel)
//                .build();
//    }
//}
