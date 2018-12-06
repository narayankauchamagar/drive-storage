package com.cooksys.ftd.drivestorageorange.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cooksys.ftd.drivestorageorange.dtos.FolderDTO;
import com.cooksys.ftd.drivestorageorange.dtos.FolderViewDTO;
import com.cooksys.ftd.drivestorageorange.services.FolderService;

@RestController
@RequestMapping("folder")
public class FolderController {
	
	@Autowired
	FolderService folderService;
	
	
	/**
	 * Create a new empty folder
	 * 
	 * @param name
	 * @return FolderDTO of newly created folder
	 */
	@PostMapping("create/{name}")
	public FolderViewDTO createFolder(@PathVariable("name") String name) {
		return this.folderService.createFolder(name);
	}
	
	/**
	 * [NOT FULLY IMPLEMENTED]Upload a new folder
	 * 
	 * @return FolderDTO of newly uploaded folder
	 */
	@PostMapping("")
	public FolderViewDTO uploadFolder(@RequestParam("name") String name, @RequestParam("file") Map<String, MultipartFile> uploadFolder) {
		FolderViewDTO newUpload = this.folderService.uploadFolder(name, uploadFolder);

		if (newUpload != null) {
			return newUpload;
		}
		return null;
	}
	
	/**
	 * Returns a folder via UID, if it exists
	 * 
	 * @return FolderDTO
	 * @see FolderDTO
	 */
	@GetMapping("{uid}")
	public FolderViewDTO getFolder(@PathVariable("uid") Long uid) {
		return this.folderService.getFolderByUID(uid);
	}
	
	/**
	 * [NOT FULLY IMPLEMENTED] download a folder's data
	 * 
	 * @param uid      of folder to download
	 * @param response for interaction with client
	 * @see HttpServletResponse
	 */
	@GetMapping("{uid}/download")
	public FolderDTO downloadFolder(@PathVariable("uid") Long uid, HttpServletResponse response) {
		return this.folderService.downloadFolder(uid);
	}
	
	/**
	 * Returns all folders
	 * 
	 * @return all FolderDTOs
	 */
	@GetMapping("")
	public List<FolderViewDTO> getAllFolders() {
		return this.folderService.getAllFolders();
	}

	/**
	 * Renames a folder by UID
	 * 
	 * @param uid     of folder to rename
	 * @param newName to be assigned to folder
	 */
	@PatchMapping("{uid}/rename/{newName}")
	public FolderViewDTO renameFolder(@PathVariable("uid") Long uid, @PathVariable("newName") String newName) {
		return this.folderService.renameFolder(uid, newName);
	}
	
	/**
	 * Moves a folder to the trash via UID
	 * 
	 * @param uid of folder to move to trash
	 */
	@DeleteMapping("{uid}")
	public FolderViewDTO trashFolder(@PathVariable("uid") Long uid) {
		return this.folderService.trashFolder(uid);
	}
	
	/**
	 * Moves a folder to the root folder
	 * 
	 * @param folderUid of folder being moved
	 */
	@PatchMapping("{uid}/move")
	public FolderViewDTO moveFolderToRoot(@PathVariable("uid") Long uid) {
		return this.folderService.moveFolder(uid);
	}

	/**
	 * Moves a folder into a folder via UID
	 * 
	 * @param folderUid of folder being moved
	 * @param folderUid of destination being moved to
	 */
	@PatchMapping("{folderUid}/move/{containerUid}")
	public FolderViewDTO moveFolder(@PathVariable("folderUid") Long folderUid, @PathVariable("containerUid") Long containerUid) {
		return this.folderService.moveFolder(folderUid, containerUid);
	}

}
