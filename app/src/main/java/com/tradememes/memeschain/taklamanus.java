package com.tradememes.memeschain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class taklamanus extends AppCompatActivity {
    ImageButton i1, i2,i3,i4,i5,i6,i7,i8,i9,i10,i11,i12,i13,i14,i15,i16,i17,i18;
    String link1, link2,link3,link4,link5,link6,link7,link8,link9,link10,link11,link12,link13
            ,link14,link15,link16,link17,link18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taklamanus);
        i1 = findViewById(R.id.imageButton);
        i2 = findViewById(R.id.imageButton2);
        i3 = findViewById(R.id.imageButton4);
        i4 = findViewById(R.id.imageButton5);
        i5 = findViewById(R.id.imageButton6);
        i6 = findViewById(R.id.imageButton7);
        i7 = findViewById(R.id.imageButton8);
        i8 = findViewById(R.id.imageButton9);
        i9 = findViewById(R.id.imageButton12);
        i10 = findViewById(R.id.imageButton10);
        i11 = findViewById(R.id.imageButton11);
        i12 = findViewById(R.id.imageButton15);
        i13 = findViewById(R.id.imageButton17);
        i14 = findViewById(R.id.imageButton16);
        i15 = findViewById(R.id.imageButton18);
        i16 = findViewById(R.id.imageButton14);
        i17 = findViewById(R.id.imageButton19);
        i18 = findViewById(R.id.imageButton13);

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                link1 = "https://firebasestorage.googleapis.com/v0/b/sahara-cdbe5.appspot.com/o/profilepic%2FHindustaniBhau.jpg?alt=media&token=c3f9ee2c-3684-42e6-bb8a-7380108cffac";

                FirebaseDatabase.getInstance().getReference().child("propic" + FirebaseAuth.getInstance().getUid()).child("url")
                        .setValue(link1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(taklamanus.this, "Profile pic uploaded successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(taklamanus.this, profile.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(taklamanus.this, "Action failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                link2 = "https://firebasestorage.googleapis.com/v0/b/sahara-cdbe5.appspot.com/o/profilepic%2Ftakla.jpg?alt=media&token=95e57551-cf2a-497f-b7c8-b7b387bfca4b";
                FirebaseDatabase.getInstance().getReference().child("propic" + FirebaseAuth.getInstance().getUid()).child("url")
                        .setValue(link2).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(taklamanus.this, "Profile pic uploaded successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(taklamanus.this, profile.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(taklamanus.this, "Action failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                link3 = "https://firebasestorage.googleapis.com/v0/b/sahara-cdbe5.appspot.com/o/profilepic%2Fblank.png?alt=media&token=27c00ee6-7159-4fc0-8b8b-f3077b5e576f";
                FirebaseDatabase.getInstance().getReference().child("propic" + FirebaseAuth.getInstance().getUid()).child("url")
                        .setValue(link3).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(taklamanus.this, "Profile pic uploaded successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(taklamanus.this, profile.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(taklamanus.this, "Action failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                link4 = "https://firebasestorage.googleapis.com/v0/b/sahara-cdbe5.appspot.com/o/profilepic%2Fharmonium.jpg?alt=media&token=9a9b8373-f02d-4f8e-862d-bddcae233105";
                FirebaseDatabase.getInstance().getReference().child("propic" + FirebaseAuth.getInstance().getUid()).child("url")
                        .setValue(link4).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(taklamanus.this, "Profile pic uploaded successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(taklamanus.this, profile.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(taklamanus.this, "Action failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        i5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                link5 = "https://firebasestorage.googleapis.com/v0/b/sahara-cdbe5.appspot.com/o/profilepic%2Fabhijit.jpg?alt=media&token=c08ea7c1-5fc2-4a86-9847-26a816751441";
                FirebaseDatabase.getInstance().getReference().child("propic" + FirebaseAuth.getInstance().getUid()).child("url")
                        .setValue(link5).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(taklamanus.this, "Profile pic uploaded successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(taklamanus.this, profile.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(taklamanus.this, "Action failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        i6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                link6 = "https://firebasestorage.googleapis.com/v0/b/sahara-cdbe5.appspot.com/o/profilepic%2Fpramod.jpg?alt=media&token=785e6b71-4468-4089-994b-e0e7a57a45d8";
                FirebaseDatabase.getInstance().getReference().child("propic" + FirebaseAuth.getInstance().getUid()).child("url")
                        .setValue(link6).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(taklamanus.this, "Profile pic uploaded successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(taklamanus.this, profile.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(taklamanus.this, "Action failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        i7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                link7 = "https://firebasestorage.googleapis.com/v0/b/sahara-cdbe5.appspot.com/o/profilepic%2Fpakistani.jpg?alt=media&token=53c43028-5305-4e4a-861b-56c432f49d10";
                FirebaseDatabase.getInstance().getReference().child("propic" + FirebaseAuth.getInstance().getUid()).child("url")
                        .setValue(link7).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(taklamanus.this, "Profile pic uploaded successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(taklamanus.this, profile.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(taklamanus.this, "Action failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        i8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                link8 = "https://firebasestorage.googleapis.com/v0/b/sahara-cdbe5.appspot.com/o/profilepic%2Fchalchalbaapkomatsikha.png?alt=media&token=e71a577f-d838-4490-95ca-3c6641af61c0";
                FirebaseDatabase.getInstance().getReference().child("propic" + FirebaseAuth.getInstance().getUid()).child("url")
                        .setValue(link8).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(taklamanus.this, "Profile pic uploaded successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(taklamanus.this, profile.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(taklamanus.this, "Action failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        i9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                link9 = "https://firebasestorage.googleapis.com/v0/b/sahara-cdbe5.appspot.com/o/profilepic%2Fsatvifail.jpg?alt=media&token=1b304dba-7e07-4fc9-8b38-9f63a2bea6ac";
                FirebaseDatabase.getInstance().getReference().child("propic" + FirebaseAuth.getInstance().getUid()).child("url")
                        .setValue(link9).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(taklamanus.this, "Profile pic uploaded successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(taklamanus.this, profile.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(taklamanus.this, "Action failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        i10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                link10 = "https://firebasestorage.googleapis.com/v0/b/sahara-cdbe5.appspot.com/o/profilepic%2Fviraj.jpg?alt=media&token=7ff0fcff-11b2-4908-a44a-8f3044ca0015";
                FirebaseDatabase.getInstance().getReference().child("propic" + FirebaseAuth.getInstance().getUid()).child("url")
                        .setValue(link10).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(taklamanus.this, "Profile pic uploaded successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(taklamanus.this, profile.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(taklamanus.this, "Action failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        i11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                link11 = "https://firebasestorage.googleapis.com/v0/b/sahara-cdbe5.appspot.com/o/profilepic%2Frockysupersyar.jpg?alt=media&token=4fd251df-0814-40d2-9b35-00b5b30325b7";
                FirebaseDatabase.getInstance().getReference().child("propic" + FirebaseAuth.getInstance().getUid()).child("url")
                        .setValue(link11).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(taklamanus.this, "Profile pic uploaded successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(taklamanus.this, profile.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(taklamanus.this, "Action failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        i12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                link12 = "https://firebasestorage.googleapis.com/v0/b/sahara-cdbe5.appspot.com/o/profilepic%2Fheymaaemes.jpeg?alt=media&token=46de851b-d21d-4d31-ae07-8a17e393e0b4";
                FirebaseDatabase.getInstance().getReference().child("propic" + FirebaseAuth.getInstance().getUid()).child("url")
                        .setValue(link12).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(taklamanus.this, "Profile pic uploaded successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(taklamanus.this, profile.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(taklamanus.this, "Action failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        i13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                link13 = "https://firebasestorage.googleapis.com/v0/b/sahara-cdbe5.appspot.com/o/profilepic%2Fdayapatalagao.jpg?alt=media&token=a1ea18ab-3b60-4f51-aef3-859d864a770e";
                FirebaseDatabase.getInstance().getReference().child("propic" + FirebaseAuth.getInstance().getUid()).child("url")
                        .setValue(link13).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(taklamanus.this, "Profile pic uploaded successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(taklamanus.this, profile.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(taklamanus.this, "Action failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        i14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                link14 = "https://firebasestorage.googleapis.com/v0/b/sahara-cdbe5.appspot.com/o/profilepic%2Fdadaji.jpg?alt=media&token=b9c604e3-1634-45da-9e6e-ef401ebf2730";
                FirebaseDatabase.getInstance().getReference().child("propic" + FirebaseAuth.getInstance().getUid()).child("url")
                        .setValue(link14).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(taklamanus.this, "Profile pic uploaded successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(taklamanus.this, profile.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(taklamanus.this, "Action failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        i15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                link15 = "https://firebasestorage.googleapis.com/v0/b/sahara-cdbe5.appspot.com/o/profilepic%2Ftom.jpg?alt=media&token=2e601dd3-8358-419c-987e-78f59bd75707";
                FirebaseDatabase.getInstance().getReference().child("propic" + FirebaseAuth.getInstance().getUid()).child("url")
                        .setValue(link15).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(taklamanus.this, "Profile pic uploaded successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(taklamanus.this, profile.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(taklamanus.this, "Action failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        i16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                link16 = "https://firebasestorage.googleapis.com/v0/b/sahara-cdbe5.appspot.com/o/profilepic%2Fbikgayigormint.jpg?alt=media&token=27ff222e-2b23-46d6-b0d4-8042c3042e33";
                FirebaseDatabase.getInstance().getReference().child("propic" + FirebaseAuth.getInstance().getUid()).child("url")
                        .setValue(link16).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(taklamanus.this, "Profile pic uploaded successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(taklamanus.this, profile.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(taklamanus.this, "Action failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        i17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                link17 = "https://firebasestorage.googleapis.com/v0/b/sahara-cdbe5.appspot.com/o/profilepic%2Fjerry.jpg?alt=media&token=eebf790a-9bb2-456a-ba34-cb10f6f1e6bf";
                FirebaseDatabase.getInstance().getReference().child("propic" + FirebaseAuth.getInstance().getUid()).child("url")
                        .setValue(link17).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(taklamanus.this, "Profile pic uploaded successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(taklamanus.this, profile.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(taklamanus.this, "Action failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        i18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                link18 = "https://firebasestorage.googleapis.com/v0/b/sahara-cdbe5.appspot.com/o/profilepic%2Fxavier.jpg?alt=media&token=2bb1f777-f977-4f41-827a-779415d5129c";
                FirebaseDatabase.getInstance().getReference().child("propic" + FirebaseAuth.getInstance().getUid()).child("url")
                        .setValue(link18).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(taklamanus.this, "Profile pic uploaded successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(taklamanus.this, profile.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(taklamanus.this, "Action failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}