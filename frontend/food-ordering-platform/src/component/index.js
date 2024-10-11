import React, { useEffect } from 'react';
import { Container, AppBar, Typography, Button, Box, Grid, Paper } from '@mui/material';
import { Link, useNavigate } from 'react-router-dom';

const LandingPage = () => {
  const navigate = useNavigate();

  useEffect(() => {

    const token = localStorage.getItem('token');
    if (token) {
      navigate('/home');
    }
  }, [navigate]);
  return (
    <>

      {/* Hero Section */}
      <Box
        sx={{
          backgroundImage: `url('https://img.freepik.com/free-photo/flat-lay-arrangement-with-salad-box-sauce_23-2148247883.jpg?t=st=1728634958~exp=1728635558~hmac=7952635ebb6a60b82552f1bc830f96e40ba31930b702ff55ef02132478cfa64d')`, // Add a hero image here
          backgroundSize: 'cover',
          backgroundPosition: 'center',
          height: '70vh',
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
        }}
      >
        <Box textAlign="center" color="white">
          <Typography variant="h2" fontWeight="bold" gutterBottom>
            Savor the Best, Delivered Fast
          </Typography>
          <Typography variant="h6" mb={4}>
            Discover your favorite meals and get them delivered to your door in no time.
          </Typography>
          <Button
            component={Link}
            to="/register"
            size="large"
            variant="contained"
            color="secondary"
            sx={{ mx: 2 }}
          >
            Get Started
          </Button>
          <Button
            component={Link}
            to="/"
            size="large"
            variant="outlined"
            color="inherit"
            sx={{ mx: 2, borderColor: 'white', color: 'white' }}
          >
            Explore Menu
          </Button>
        </Box>
      </Box>

      {/* Featured Services */}
      <Container maxWidth="md" sx={{ mt: 8, textAlign: 'center' }}>
        <Typography variant="h4" color="primary" fontWeight="bold" gutterBottom>
          Why Choose Us?
        </Typography>
        <Grid container spacing={4} mt={4}>
          <Grid item xs={12} sm={4}>
            <Paper elevation={3} sx={{ p: 4 }}>
              <Typography variant="h6" fontWeight="bold" gutterBottom>
                Fast Delivery
              </Typography>
              <Typography>
                Get your food delivered quickly with our lightning-fast delivery service.
              </Typography>
            </Paper>
          </Grid>
          <Grid item xs={12} sm={4}>
            <Paper elevation={3} sx={{ p: 4 }}>
              <Typography variant="h6" fontWeight="bold" gutterBottom>
                Fresh Ingredients
              </Typography>
              <Typography>
                We prioritize freshness to make sure every meal is made with the best ingredients.
              </Typography>
            </Paper>
          </Grid>
          <Grid item xs={12} sm={4}>
            <Paper elevation={3} sx={{ p: 4 }}>
              <Typography variant="h6" fontWeight="bold" gutterBottom>
                Easy to Order
              </Typography>
              <Typography>
                Our platform makes ordering food easy with just a few clicks.
              </Typography>
            </Paper>
          </Grid>
        </Grid>
      </Container>

      {/* Footer */}
      <Box mt={8} py={4} textAlign="center" bgcolor="grey.900" color="white">
        <Typography variant="body2" gutterBottom>
          &copy; 2024 Tiffin Smart - All Rights Reserved.
        </Typography>
        <Typography variant="body2">
          <Button component={Link} to="/terms" color="inherit">
            Terms & Conditions
          </Button>
          {' | '}
          <Button component={Link} to="/privacy" color="inherit">
            Privacy Policy
          </Button>
        </Typography>
      </Box>
    </>
  );
};

export default LandingPage;
