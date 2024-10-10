import React from 'react';
import { Container, AppBar, Typography, Button, Box, Grid, Paper } from '@mui/material';
import Navbar from './nav';
import RegisterPage from './Register';

const LandingPage = (isToken) => {
  return (
    <>
    <Navbar isToken={isToken}/>
     <Container maxWidth="lg">
      <AppBar position="static" color="primary">
        <Box p={2}>
          <Typography variant="h4" align="center" color="inherit">
            TIFFIN SMART
          </Typography>
        </Box>
      </AppBar>
      
      <Box my={4} display="flex" justifyContent="center">
        <Paper elevation={3} style={{ padding: '30px', textAlign: 'center' }}>
          <Typography variant="h3" gutterBottom>
            Welcome to Your Favorite Food Hub!
          </Typography>
          <Typography variant="body1" paragraph>
            Order delicious food from your favorite restaurants and enjoy a seamless delivery experience.
          </Typography>
          
          <Grid container spacing={2} justifyContent="center">
            <Grid item>
              <Button variant="contained" color="primary" href="/register">
                Register
              </Button>
            </Grid>
            <Grid item>
              <Button variant="contained" color="success" href="/login">
                Login
              </Button>
            </Grid>
            <Grid item>
              <Button variant="contained" color="secondary" href="/inventory">
                Browse Menu
              </Button>
            </Grid>
          </Grid>
        </Paper>
      </Box>

      <Box mt={4} mb={2} textAlign="center">
        <Typography variant="body2" color="textSecondary">
          &copy; 2024 Food Ordering Platform. All Rights Reserved.
        </Typography>
      </Box>
    </Container>
    </>
   
  );
};

export default LandingPage;
