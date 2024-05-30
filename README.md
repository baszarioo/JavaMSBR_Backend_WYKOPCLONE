# JavaMSBR_Backend_WYKOPCLONE
SPRINGBOOT-APP Wykop clone (wannabe) [BACKEND]


# SIMPLE DOC:
## UserDetailsServiceRev implements UserDetailsService *
UserDetailsServiceRev class implements the UserDetailsService interface provided by Spring Security. The primary responsibility of this interface is to fetch user details to be used for authentication.
loadUserByUsername(String username)

Purpose: This is the core method from the UserDetailsService interface. Spring Security calls this method when it needs to authenticate a user.
Logic:
userRepository.findByUsername(username): Fetches a user from the database (your UserRepository) based on the given username.
orElseThrow(...): If no user is found, it throws a UserNameNotFoundException to signal an authentication failure.
Returns a new org.springframework.security.core.userdetails.User: This is Spring Security's own User object, not your application's User. It constructs this object using:
The user's username and password from the fetched User entity.
user.isEnabled(): Sets account activation status.
true: The following three boolean values indicate that the account is non-expired, credentials are non-expired, and the account is not locked.
getAuthorities("USER"): Grants the authority/role of "USER" to the authenticated user.
getAuthorities(String role)

Purpose: A helper method to convert a role (String) to a GrantedAuthority needed for Spring Security.
Logic: Creates a SimpleGrantedAuthority based on the role and returns it in a collection.


/api/comments/	POST	createComments
/api/comments/by-postId/{postId}	GET	getAllCommentsForPost
/api/comments/by-user/{userName}	GET	getCommentsByUsername