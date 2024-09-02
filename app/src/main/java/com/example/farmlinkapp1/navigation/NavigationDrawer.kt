package com.example.farmlinkapp1.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.farmlinkapp1.R

@Composable
fun NavigationDrawer(
    drawerState: DrawerState,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Image(
                    modifier = Modifier.size(250.dp),
                    painter = painterResource(id = R.drawable.google_logo),
                    contentDescription = null
                )

                NavigationDrawerItem(
                    label = {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier.size(250.dp),
                                imageVector = Icons.Default.Home,
                                contentDescription = null
                            )
                        }

                        Spacer(Modifier.width(12.dp))
                        Text("Home")
                    },
                    selected = true,
                    onClick = { /*TODO*/ })
            }
        },
        content = content
    )
}